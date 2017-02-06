package uk.co.onsdigital.discovery.model;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.UUID;

@Entity
@Table(name = "dimension_value", uniqueConstraints = @UniqueConstraint(columnNames={"dimensional_data_set_id", "name", "value"}))
public class DimensionValue {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "dimensional_data_set_id", columnDefinition = "uuid", nullable = false)
    private UUID dimensionalDataSetId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "hierarchy_entry_id")
    @JoinFetch(JoinFetchType.OUTER)
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
