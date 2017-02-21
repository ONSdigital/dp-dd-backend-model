package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The persistent class for the dimensional_data_point database table.
 */
@Entity
@Table(name = "dimensional_data_point")
@NamedQuery(name = DimensionalDataPoint.FIND_ALL_QUERY, query = "SELECT d FROM DimensionalDataPoint d")
public class DimensionalDataPoint implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL_QUERY = "DimensionalDataPoint.findAll";

    @EmbeddedId
    private DimensionalDataPointPK id;

    @Column(name = "data_marking")
    private String dataMarking;

    @Column(name = "observation_type_value")
    private String observationTypeValue;

    private BigDecimal value;

    //bi-directional many-to-one association to DimensionalDataSet
    @ManyToOne
    @JoinColumn(name = "dimensional_data_set_id")
    private DimensionalDataSet dimensionalDataSet;

    //bi-directional many-to-one association to ObservationType
    @ManyToOne
    @JoinColumn(name = "observation_type")
    private ObservationType observationType;

    //bi-directional many-to-one association to Population
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "geographic_area_id", referencedColumnName = "geographic_area_id"),
            @JoinColumn(name = "time_period_id", referencedColumnName = "time_period_id")
    })
    private Population population;

    //bi-directional many-to-one association to Variable
    @ManyToOne
    @JoinColumn(name = "variable_id")
    private Variable variable;

    public DimensionalDataPoint() {
    }

    public DimensionalDataPoint(DimensionalDataSet dimensionalDataSet, BigDecimal value, Population population, Variable variable) {
        this.value = value;
        this.dimensionalDataSet = dimensionalDataSet;
        this.population = population;
        this.variable = variable;
    }

    public DimensionalDataPointPK getId() {
        return this.id;
    }

    public void setId(DimensionalDataPointPK id) {
        this.id = id;
    }

    public String getDataMarking() {
        return this.dataMarking;
    }

    public void setDataMarking(String dataMarking) {
        this.dataMarking = dataMarking;
    }

    public String getObservationTypeValue() {
        return this.observationTypeValue;
    }

    public void setObservationTypeValue(String observationTypeValue) {
        this.observationTypeValue = observationTypeValue;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public DimensionalDataSet getDimensionalDataSet() {
        return this.dimensionalDataSet;
    }

    public void setDimensionalDataSet(DimensionalDataSet dimensionalDataSet) {
        this.dimensionalDataSet = dimensionalDataSet;
    }

    public ObservationType getObservationType() {
        return this.observationType;
    }

    public void setObservationType(ObservationType observationType) {
        this.observationType = observationType;
    }

    public Population getPopulation() {
        return this.population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public Variable getVariable() {
        return this.variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

}