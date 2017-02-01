package uk.co.onsdigital.discovery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "dimension_value", uniqueConstraints = @UniqueConstraint(columnNames={"dimensional_data_set_id", "name", "value"}))
public class DimensionValue {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "dimensional_data_set_id", columnDefinition = "uuid", nullable = false)
    private UUID dimensionalDataSetId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "hierarchy_entry_id")
    private HierarchyEntry hierarchyEntry;

    public DimensionValue() {
    }

    public DimensionValue(UUID dimensionalDataSetId, String name, String value) {
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

}
