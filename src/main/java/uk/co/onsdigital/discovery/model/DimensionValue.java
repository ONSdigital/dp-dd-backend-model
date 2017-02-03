package uk.co.onsdigital.discovery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "dimension_value", uniqueConstraints = @UniqueConstraint(columnNames={"dimensional_data_set_id", "name", "value"}))
@NamedQueries({
        @NamedQuery(name = DimensionValue.FIND_QUERY, query = "SELECT dim FROM DimensionValue dim WHERE dim.dimensionalDataSetId = :ddsId AND dim.name = :name AND dim.value = :value")
})
public class DimensionValue {

    private static final long serialVersionUID = 1L;

    // Named query to find a DimensionValue by dataset id, name and value.
    public static final String FIND_QUERY = "DimensionValue.findByDatasetIdNameAndValue";
    // Parameter specifying the id of the dimensional dataset.
    public static final String DATASET_ID_PARAM = "ddsId";
    // Parameter specifying the name of the dimension.
    public static final String NAME_PARAM = "name";
    // Parameter specifying the value of the dimension.
    public static final String VALUE_PARAM = "value";

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "dimensional_data_set_id", columnDefinition = "uuid not null", nullable = false)
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
        this.id = UUID.randomUUID();
        this.dimensionalDataSetId = dimensionalDataSetId;
        this.name = name;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "DimensionValue{" +
                "dimensionalDataSetId=" + dimensionalDataSetId +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
