package uk.co.onsdigital.discovery.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    public DataResource(String dataResource, String title) {
        this.dataResource = dataResource;
        this.title = title;
    }

    @Id
    @Column(name = "data_resource")
    private String dataResource;

    @Column(name = "column_concept")
    private String columnConcept;

    @Embedded
    private Metadata metadata = new Metadata();

    @Column(name = "row_concept")
    private String rowConcept;

    private String title;

    //bi-directional many-to-many association to Taxonomy
    @ManyToMany
    @JoinTable(
            name = "data_resource_taxonomy"
            , joinColumns = {
            @JoinColumn(name = "data_resource")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "taxonomy")
    }
    )
    private List<Taxonomy> taxonomies;

    //bi-directional many-to-one association to DimensionalDataSet
    @OneToMany(mappedBy = "dataResourceBean", cascade = CascadeType.PERSIST)
    @OrderBy("majorVersion DESC, minorVersion DESC")
    private List<DimensionalDataSet> dimensionalDataSets;

    public DataResource() {
    }

    public String getDataResource() {
        return this.dataResource;
    }

    public void setDataResource(String dataResource) {
        this.dataResource = dataResource;
    }

    public String getColumnConcept() {
        return this.columnConcept;
    }

    public void setColumnConcept(String columnConcept) {
        this.columnConcept = columnConcept;
    }

    public Metadata getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Metadata metadata) {
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

    public List<Taxonomy> getTaxonomies() {
        return this.taxonomies;
    }

    public void setTaxonomies(List<Taxonomy> taxonomies) {
        this.taxonomies = taxonomies;
    }

    public List<DimensionalDataSet> getDimensionalDataSets() {
        return this.dimensionalDataSets;
    }

    public void setDimensionalDataSets(List<DimensionalDataSet> dimensionalDataSets) {
        this.dimensionalDataSets = dimensionalDataSets;
    }

    public DimensionalDataSet addDimensionalDataSet(DimensionalDataSet dimensionalDataSet) {
        getDimensionalDataSets().add(dimensionalDataSet);
        dimensionalDataSet.setDataResourceBean(this);

        return dimensionalDataSet;
    }

    public DimensionalDataSet removeDimensionalDataSet(DimensionalDataSet dimensionalDataSet) {
        getDimensionalDataSets().remove(dimensionalDataSet);
        dimensionalDataSet.setDataResourceBean(null);

        return dimensionalDataSet;
    }

    @Override
    public String toString() {
        return "DataResource{" +
                "dataResource='" + dataResource + '\'' +
                ", metadata=" + metadata +
                ", title='" + title + '\'' +
                ", taxonomies=" + taxonomies +
                '}';
    }
}