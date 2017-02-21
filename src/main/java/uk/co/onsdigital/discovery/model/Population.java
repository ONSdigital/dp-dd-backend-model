package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the population database table.
 */
@Entity
@NamedQuery(name = Population.FIND_ALL_QUERY, query = "SELECT p FROM Population p")
public class Population implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL_QUERY =  "Population.findAll";

    @EmbeddedId
    private PopulationPK id;

    @Column(name = "geographic_area_ext_code")
    private String geographicAreaExtCode;

    //bi-directional many-to-one association to DimensionalDataPoint
    @OneToMany(mappedBy = "population")
    private List<DimensionalDataPoint> dimensionalDataPoints;

    //bi-directional many-to-one association to GeographicArea
    @ManyToOne
    @JoinColumn(name = "geographic_area_id")
    private GeographicArea geographicArea;

    //bi-directional many-to-one association to TimePeriod
    @ManyToOne
    @JoinColumn(name = "time_period_id")
    private TimePeriod timePeriod;

    public Population() {
    }

    public PopulationPK getId() {
        return this.id;
    }

    public void setId(PopulationPK id) {
        this.id = id;
    }

    public String getGeographicAreaExtCode() {
        return this.geographicAreaExtCode;
    }

    public void setGeographicAreaExtCode(String geographicAreaExtCode) {
        this.geographicAreaExtCode = geographicAreaExtCode;
    }

    public List<DimensionalDataPoint> getDimensionalDataPoints() {
        return this.dimensionalDataPoints;
    }

    public void setDimensionalDataPoints(List<DimensionalDataPoint> dimensionalDataPoints) {
        this.dimensionalDataPoints = dimensionalDataPoints;
    }

    public DimensionalDataPoint addDimensionalDataPoint(DimensionalDataPoint dimensionalDataPoint) {
        getDimensionalDataPoints().add(dimensionalDataPoint);
        dimensionalDataPoint.setPopulation(this);

        return dimensionalDataPoint;
    }

    public DimensionalDataPoint removeDimensionalDataPoint(DimensionalDataPoint dimensionalDataPoint) {
        getDimensionalDataPoints().remove(dimensionalDataPoint);
        dimensionalDataPoint.setPopulation(null);

        return dimensionalDataPoint;
    }

    public GeographicArea getGeographicArea() {
        return this.geographicArea;
    }

    public void setGeographicArea(GeographicArea geographicArea) {
        this.geographicArea = geographicArea;
    }

    public TimePeriod getTimePeriod() {
        return this.timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

}