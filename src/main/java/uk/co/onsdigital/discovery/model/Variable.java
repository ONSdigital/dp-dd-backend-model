package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the variable database table.
 */
@Entity
@NamedQuery(name = "Variable.findAll", query = "SELECT v FROM Variable v")
@Table(indexes = {@Index(columnList = "name")})
public class Variable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "varseq", sequenceName = "varseq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "varseq")
    @Column(name = "variable_id")
    private Long id;

    @Column(name = "ext_code")
    private String extCode;

    private String metadata;

    private String name;

    //bi-directional many-to-one association to DimensionalDataPoint
    @OneToMany(mappedBy = "variable")
    private List<DimensionalDataPoint> dimensionalDataPoints;

    //bi-directional many-to-one association to UnitType
    @ManyToOne
    @JoinColumn(name = "unit_type")
    private UnitType unitType;

    //bi-directional many-to-one association to ValueDomain
    @ManyToOne
    @JoinColumn(name = "value_domain")
    private ValueDomain valueDomain;

    //bi-directional many-to-many association to Category
    @ManyToMany
    @JoinTable(
            name = "variable_category"
            , joinColumns = {
            @JoinColumn(name = "variable_id")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "category_id")
    }
    )
    private List<Category> categories;

    public Variable() {
    }

    public Variable(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExtCode() {
        return this.extCode;
    }

    public void setExtCode(String extCode) {
        this.extCode = extCode;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DimensionalDataPoint> getDimensionalDataPoints() {
        return this.dimensionalDataPoints;
    }

    public void setDimensionalDataPoints(List<DimensionalDataPoint> dimensionalDataPoints) {
        this.dimensionalDataPoints = dimensionalDataPoints;
    }

    public DimensionalDataPoint addDimensionalDataPoint(DimensionalDataPoint dimensionalDataPoint) {
        getDimensionalDataPoints().add(dimensionalDataPoint);
        dimensionalDataPoint.setVariable(this);

        return dimensionalDataPoint;
    }

    public DimensionalDataPoint removeDimensionalDataPoint(DimensionalDataPoint dimensionalDataPoint) {
        getDimensionalDataPoints().remove(dimensionalDataPoint);
        dimensionalDataPoint.setVariable(null);

        return dimensionalDataPoint;
    }

    public UnitType getUnitType() {
        return this.unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public ValueDomain getValueDomain() {
        return this.valueDomain;
    }

    public void setValueDomain(ValueDomain valueDomain) {
        this.valueDomain = valueDomain;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}