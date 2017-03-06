package uk.co.onsdigital.discovery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the data_resource database table.
 */
@Entity
@Table(name = "data_resource")
@NamedQueries({
        @NamedQuery(name = DataResource.FIND_ALL_QUERY, query = "SELECT d FROM DataResource d"),
        @NamedQuery(name = DataResource.COUNT_ALL, query = "SELECT Count(d) FROM DataResource d")
})
public class DataResource implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL_QUERY = "DataResource.findAll";
    public static final String COUNT_ALL = "DataResource.count";

    public DataResource(String id, String title) {
        this.id = id;
        this.title = title;
    }

    @Id
    @Column(name = "data_resource")
    private String id;

    @Column(name = "column_concept")
    private String columnConcept;

    private String metadata;

    @Column(name = "row_concept")
    private String rowConcept;

    private String title;

    //bi-directional many-to-one association to DimensionalDataSet
    @OneToMany(mappedBy = "dataResource", cascade = CascadeType.PERSIST)
    @OrderBy("majorVersion DESC, minorVersion DESC")
    private List<DataSet> dataSets;

    public DataResource() {
    }

    public String getId() {
        return this.id;
    }

    public void setDataResource(String dataResource) {
        this.id = id;
    }

    public String getColumnConcept() {
        return this.columnConcept;
    }

    public void setColumnConcept(String columnConcept) {
        this.columnConcept = columnConcept;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getRowConcept() {
        return this.rowConcept;
    }

    public void setRowConcept(String rowConcept) {
        this.rowConcept = rowConcept;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DataSet> getDataSets() {
        return this.dataSets;
    }

    public void setDataSets(List<DataSet> dataSets) {
        this.dataSets = dataSets;
    }

    public DataSet addDimensionalDataSet(DataSet dataSet) {
        getDataSets().add(dataSet);
        dataSet.setDataResource(this);

        return dataSet;
    }

    public DataSet removeDimensionalDataSet(DataSet dataSet) {
        getDataSets().remove(dataSet);
        dataSet.setDataResource(null);

        return dataSet;
    }

    @Override
    public String toString() {
        return "DataResource{" +
                "dataResource='" + id + '\'' +
                ", metadata=" + metadata +
                ", title='" + title + '\'' +
                '}';
    }
}