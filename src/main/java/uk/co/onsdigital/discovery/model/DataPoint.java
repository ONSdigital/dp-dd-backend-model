package uk.co.onsdigital.discovery.model;


import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class DataPoint {


    @Id
    private UUID id;

    private float observation;

    @Column(name = "observation_type_value")
    private float observationTypeValue;

    @Column(name = "data_marking")
    private String dataMarking;

    @ManyToMany
    @JoinTable(
            name = "dimension_datapoint"
            , joinColumns = {
            @JoinColumn(name = "id")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "dds_id", referencedColumnName = "dimensional_data_set_id"),
            @JoinColumn(name = "dimension_name", referencedColumnName = "name"),
            @JoinColumn(name = "dimension_value", referencedColumnName = "value")
    }
    )
    private List<DimensionValue> dimensionValues;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public float getObservation() {
        return observation;
    }

    public void setObservation(float observation) {
        this.observation = observation;
    }

    public float getObservationTypeValue() {
        return observationTypeValue;
    }

    public void setObservationTypeValue(float observationTypeValue) {
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
