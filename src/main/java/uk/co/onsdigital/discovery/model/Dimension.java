package uk.co.onsdigital.discovery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * A dimension in a dataset.
 */
@Entity
@Table(name = "dimension")
@IdClass(Dimension.DimensionPK.class)
public class Dimension {

    @Id
    @ManyToOne
    @JoinColumn(name = "dimensional_data_set_id", columnDefinition = "uuid")
    private DimensionalDataSet dataSet;

    @Id
    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumns({
            @JoinColumn(name = "dimensional_data_set_id", referencedColumnName = "dimensional_data_set_id", columnDefinition = "uuid", insertable = false, updatable = false),
            @JoinColumn(name = "name", referencedColumnName = "name", insertable = false, updatable = false)
    })
    private List<DimensionValue> values;

    @ManyToOne
    @JoinColumn(name = "hierarchy_id", referencedColumnName = "id")
    private Hierarchy hierarchy;

    public Dimension() {
        // Default constructor for JPA
    }

    public Dimension(DimensionalDataSet dataSet, String name, DimensionValue... values) {
        this.dataSet = dataSet;
        this.name = name;
        this.values = Stream.of(values).peek(value -> value.setDimension(this)).collect(toList());
    }

    public DimensionalDataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DimensionalDataSet dataSet) {
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

    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    @Override
    public String toString() {
        return "Dimension{" +
                "dataSet=" + dataSet +
                ", name='" + name + '\'' +
                ", hierarchy=" + hierarchy +
                '}';
    }

    /**
     * Composite primary key class.
     */
    public static class DimensionPK implements Serializable {
        private static final long serialVersionUID = 1L;
        private UUID dataSet;
        private String name;

        public DimensionPK() {
            // Default constructor for JPA
        }

        public DimensionPK(DimensionalDataSet dataSet, String name) {
            this.dataSet = dataSet.getId();
            this.name = name;
        }

        @Override
        public boolean equals(Object that) {
            return this == that || that instanceof DimensionPK
                    && Objects.equals(this.name, ((DimensionPK) that).name)
                    && Objects.equals(this.dataSet, ((DimensionPK) that).dataSet);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dataSet, name);
        }
    }

}
