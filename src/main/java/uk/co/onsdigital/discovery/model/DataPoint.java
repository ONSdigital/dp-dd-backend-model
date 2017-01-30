package uk.co.onsdigital.discovery.model;


import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class DataPoint {


    @Id
    private UUID id;

    private int observation;

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
    private List<Dimension> dimensions;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getObservation() {
        return observation;
    }

    public void setObservation(int observation) {
        this.observation = observation;
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }
}
