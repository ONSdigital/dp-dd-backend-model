package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

import static uk.co.onsdigital.discovery.model.DimensionalDataSetRowIndex.COUNT_QUERY;
import static uk.co.onsdigital.discovery.model.DimensionalDataSetRowIndex.DELETE_QUERY;

@Entity
@Table(name = "dimensional_data_set_row_index")
@NamedQueries({
        @NamedQuery(name = COUNT_QUERY, query = "SELECT COUNT(r) FROM DimensionalDataSetRowIndex r WHERE r.datasetId = :datasetId"),
        @NamedQuery(name = DELETE_QUERY, query = "DELETE FROM DimensionalDataSetRowIndex r WHERE r.datasetId = :datasetId")
})
public class DimensionalDataSetRowIndex implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    // The query to count the number of rows processed for a dataset.
    public static final String COUNT_QUERY = "DimensionalDataSetRowIndex.countForDataset";
    // The query to delete row indexes for a dataset.
    public static final String DELETE_QUERY = "DimensionalDataSetRowIndex.deleteForDataset";

    // The name of the dataset parameter to pass for both queries.
    public static final String DATASET_PARAMETER = "datasetId";

    @Id
    @Column(name = "dimensional_data_set_id", columnDefinition = "uuid")
    private UUID datasetId;

    @Id
    @Column(name = "row_index")
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
