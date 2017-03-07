package uk.co.onsdigital.discovery.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
public class DataPoint {

    @Id
    @Column(columnDefinition = "uuid")
    @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(columnDefinition = "numeric")
    private BigDecimal observation;

    @Column(name = "observation_type_value")
    private String observationTypeValue;

    @Column(name = "data_marking")
    private String dataMarking;

    @ManyToMany
    @JoinTable(
            name = "dimension_value_datapoint"
            , joinColumns = {@JoinColumn(name = "datapoint_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "dimension_value_id", referencedColumnName = "id")}
    )
    private List<DimensionValue> dimensionValues;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getObservation() {
        return observation;
    }

    public void setObservation(BigDecimal observation) {
        this.observation = observation;
    }

    public String getObservationTypeValue() {
        return observationTypeValue;
    }

    public void setObservationTypeValue(String observationTypeValue) {
        this.observationTypeValue = observationTypeValue;
    }

    public String getDataMarking() {
        return dataMarking;
    }

    public void setDataMarking(String dataMarking) {
        this.dataMarking = dataMarking;
    }

    public List<DimensionValue> getDimensionValues() {
        return dimensionValues;
    }

    public void setDimensionValues(List<DimensionValue> dimensionValues) {
        this.dimensionValues = dimensionValues;
    }
}
