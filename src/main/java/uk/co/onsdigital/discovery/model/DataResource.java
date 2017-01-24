package uk.co.onsdigital.discovery.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the data_resource database table.
 */
@Entity
@Table(name = "data_resource")
@NamedQuery(name = "DataResource.findAll", query = "SELECT d FROM DataResource d")
public class DataResource implements Serializable {
    private static final long serialVersionUID = 1L;

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
    private List<DimensionalDataSet> dimensionalDataSets;

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

    public List<DimensionalDataSet> getDimensionalDataSets() {
        return this.dimensionalDataSets;
    }

    public void setDimensionalDataSets(List<DimensionalDataSet> dimensionalDataSets) {
        this.dimensionalDataSets = dimensionalDataSets;
    }

    public DimensionalDataSet addDimensionalDataSet(DimensionalDataSet dimensionalDataSet) {
        getDimensionalDataSets().add(dimensionalDataSet);
        dimensionalDataSet.setDataResource(this);

        return dimensionalDataSet;
    }

    public DimensionalDataSet removeDimensionalDataSet(DimensionalDataSet dimensionalDataSet) {
        getDimensionalDataSets().remove(dimensionalDataSet);
        dimensionalDataSet.setDataResource(null);

        return dimensionalDataSet;
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