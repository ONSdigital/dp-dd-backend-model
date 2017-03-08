package uk.co.onsdigital.discovery.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * A dimension in a dataset.
 */
@Entity
@Table(name = "dimension", uniqueConstraints = {
        @UniqueConstraint(name = "unq_dimension_data_set_id_name", columnNames = {"data_set_id", "name"})
})
@NamedQueries({
        @NamedQuery(
                name = Dimension.FIND_BY_DATA_SET_AND_NAME,
                query = "SELECT d FROM Dimension d WHERE d.dataSet.id = :dataSetId AND d.name = :name"
        )
})
public class Dimension {
    public static final String FIND_BY_DATA_SET_AND_NAME = "Dimension.findByDataSetAndName";
    public static final String DATA_SET_PARAM = "dataSetId";
    public static final String NAME_PARAM = "name";

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "data_set_id", columnDefinition = "uuid")
    private DataSet dataSet;

    @Column(name = "name")
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(mappedBy="dimension")
    private List<DimensionValue> values;

    public Dimension() {
        // Default constructor for JPA
    }

    public Dimension(DataSet dataSet, String name, DimensionValue... values) {
        this.dataSet = dataSet;
        this.name = name;
        this.values = Stream.of(values).peek(value -> value.setDimension(this)).collect(toList());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DimensionValue> getValues() {
        return values;
    }

    public void setValues(List<DimensionValue> values) {
        this.values = values;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHierarchical() {
        return !Hierarchy.TYPE_NON_HIERARCHICAL.equals(type);
    }

    @Override
    public String toString() {
        return "Dimension{" +
                "dataSet=" + dataSet +
                ", name='" + name + "'" +
                ", type='" + type + "'" +
                '}';
    }

}
