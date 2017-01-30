package uk.co.onsdigital.discovery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "dimension", uniqueConstraints = @UniqueConstraint(columnNames={"dimensional_data_set_id", "name", "value"}))
@IdClass(Dimension.DimensionId.class)
public class Dimension {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "dimensional_data_set_id", columnDefinition = "uuid")
    private UUID dimensionalDataSetId;

    @Id
    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumns ({
        @JoinColumn(name = "hierarchy_entry_hierarchy_id", referencedColumnName="hierarchy_id"),
        @JoinColumn(name = "hierarchy_entry_value_code", referencedColumnName="value_code")
    })
    private HierarchyEntry hierarchyEntry;

    public Dimension() {
    }

    public Dimension(UUID dimensionalDataSetId, String name, String value) {
        this.dimensionalDataSetId = dimensionalDataSetId;
        this.name = name;
        this.value = value;
    }

    public HierarchyEntry getHierarchyEntry() {
        return hierarchyEntry;
    }

    public void setHierarchyEntry(HierarchyEntry hierarchyEntry) {
        this.hierarchyEntry = hierarchyEntry;
    }

    public UUID getDimensionalDataSetId() {
        return dimensionalDataSetId;
    }

    public void setDimensionalDataSetId(UUID dimensionalDataSetId) {
        this.dimensionalDataSetId = dimensionalDataSetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static class DimensionId implements Serializable {
        private UUID dimensionalDataSetId;
        private String name;
        private String value;

        public DimensionId() {
        }

        public DimensionId(UUID dimensionalDataSetId, String name, String value) {
            this.dimensionalDataSetId = dimensionalDataSetId;
            this.name = name;
            this.value = value;
        }

        public UUID getDimensionalDataSetId() {
            return dimensionalDataSetId;
        }

        public void setDimensionalDataSetId(UUID dimensionalDataSetId) {
            this.dimensionalDataSetId = dimensionalDataSetId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


}
