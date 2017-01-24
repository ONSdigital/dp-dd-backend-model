package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the observation_type database table.
 */
@Entity
@Table(name = "observation_type")
@NamedQuery(name = "ObservationType.findAll", query = "SELECT o FROM ObservationType o")
public class ObservationType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "observation_type")
    private String id;

    private String name;

    //bi-directional many-to-one association to DimensionalDataPoint
    @OneToMany(mappedBy = "observationType")
    private List<DimensionalDataPoint> dimensionalDataPoints;

    public ObservationType() {
    }

    public ObservationType(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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
        dimensionalDataPoint.setObservationType(this);

        return dimensionalDataPoint;
    }

    public DimensionalDataPoint removeDimensionalDataPoint(DimensionalDataPoint dimensionalDataPoint) {
        getDimensionalDataPoints().remove(dimensionalDataPoint);
        dimensionalDataPoint.setObservationType(null);

        return dimensionalDataPoint;
    }

}