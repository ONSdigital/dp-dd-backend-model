package uk.co.onsdigital.discovery.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@IdClass(DataPoint.DatapointKey.class)
public class DataPoint {

    @Id
    @Column(columnDefinition = "uuid", name = "data_set_id")
    private UUID datasetId;

    @Id
    @Column(name="row_index")
    private Long rowIndex;

    @Column(columnDefinition = "numeric")
    private BigDecimal observation;

    @Column(name = "observation_type_value")
    private String observationTypeValue;

    @Column(name = "data_marking")
    private String dataMarking;

    @ManyToMany
    @JoinTable(
            name = "dimension_value_datapoint"
            , joinColumns = {@JoinColumn(name = "data_set_id", referencedColumnName = "data_set_id"),
                             @JoinColumn(name="row_index", referencedColumnName = "row_index")}
            , inverseJoinColumns = {@JoinColumn(name = "dimension_value_id", referencedColumnName = "id")}
    )
    private List<DimensionValue> dimensionValues;

    public UUID getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(UUID datasetId) {
        this.datasetId = datasetId;
    }

    public Long getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Long rowIndex) {
        this.rowIndex = rowIndex;
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

    public static class DatapointKey implements Serializable {
        private UUID datasetId;
        private Long rowIndex;

        public UUID getDatasetId() {
            return datasetId;
        }

        public void setDatasetId(UUID datasetId) {
            this.datasetId = datasetId;
        }

        public Long getRowIndex() {
            return rowIndex;
        }

        public void setRowIndex(Long rowIndex) {
            this.rowIndex = rowIndex;
        }
    }
}
