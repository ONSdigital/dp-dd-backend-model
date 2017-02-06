package uk.co.onsdigital.discovery.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
public class DataPoint {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(columnDefinition = "numeric not null")
    private BigDecimal observation;

    @Column(name = "observation_type_value", columnDefinition = "numeric")
    private BigDecimal observationTypeValue;

    @Column(name = "data_marking")
    private String dataMarking;

    @ManyToMany
    @JoinTable(
            name = "dimension_datapoint"
            , joinColumns = {@JoinColumn(name = "id")}
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

    public BigDecimal getObservationTypeValue() {
        return observationTypeValue;
    }

    public void setObservationTypeValue(BigDecimal observationTypeValue) {
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
