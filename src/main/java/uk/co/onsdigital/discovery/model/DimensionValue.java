package uk.co.onsdigital.discovery.model;

//import org.eclipse.persistence.annotations.JoinFetch;
//import org.eclipse.persistence.annotations.JoinFetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "dimension_value", uniqueConstraints = @UniqueConstraint(columnNames={"dimension_data_set_id", "dimension_name", "value"}))
@NamedQueries({
        @NamedQuery(name = DimensionValue.FIND_QUERY, query = "SELECT dimVal FROM DimensionValue dimVal WHERE dimVal.dimension.dataSet.id = :ddsId AND dimVal.dimension.name = :name AND dimVal.value = :value")
})
public class DimensionValue {

    /** Named query to find a DimensionValue by dataset id, name and value. */
    public static final String FIND_QUERY = "DimensionValue.findByDatasetIdNameAndValue";
    /** Parameter specifying the id of the dimensional dataset. */
    public static final String DATASET_ID_PARAM = "ddsId";
    /** Parameter specifying the name of the dimension. */
    public static final String NAME_PARAM = "name";
    /** Parameter specifying the value of the dimension. */
    public static final String VALUE_PARAM = "value";

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dimension_data_set_id", referencedColumnName = "data_set_id", columnDefinition = "uuid not null", nullable = false),
            @JoinColumn(name = "dimension_name", referencedColumnName = "name", nullable = false)
    })
    private Dimension dimension;

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "hierarchy_entry_id")
//    @JoinFetch(JoinFetchTypepe.OUTER)
    @Fetch(FetchMode.JOIN)
    private HierarchyEntry hierarchyEntry;

    public DimensionValue() {
    }

    public DimensionValue(String value) {
        this.id = UUID.randomUUID();
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return "DimensionValue{" +
                "dimension=" + dimension +
                ", value='" + value + '\'' +
                '}';
    }
}
