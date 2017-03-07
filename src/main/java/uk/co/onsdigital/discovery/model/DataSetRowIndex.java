package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

import static uk.co.onsdigital.discovery.model.DataSetRowIndex.COUNT_QUERY;
import static uk.co.onsdigital.discovery.model.DataSetRowIndex.DELETE_QUERY;

@Entity
@Table(name = "data_set_row_index")
@NamedQueries({
        @NamedQuery(name = COUNT_QUERY, query = "SELECT COUNT(r) FROM DataSetRowIndex r WHERE r.datasetId = :datasetId"),
        @NamedQuery(name = DELETE_QUERY, query = "DELETE FROM DataSetRowIndex r WHERE r.datasetId = :datasetId")
})
@IdClass(DataSetRowIndex.RowIndexId.class)
public class DataSetRowIndex implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    // The query to count the number of rows processed for a dataset.
    public static final String COUNT_QUERY = "DataSetRowIndex.countForDataset";
    // The query to delete row indexes for a dataset.
    public static final String DELETE_QUERY = "DataSetRowIndex.deleteForDataset";

    // The name of the dataset parameter to pass for both queries.
    public static final String DATASET_PARAMETER = "datasetId";

    @Id
    @Column(name = "data_set_id", columnDefinition = "uuid")
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

    static class RowIndexId  implements Serializable {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            RowIndexId that = (RowIndexId) o;

            if (datasetId != null ? !datasetId.equals(that.datasetId) : that.datasetId != null) {
                return false;
            }
            return rowIndex != null ? rowIndex.equals(that.rowIndex) : that.rowIndex == null;
        }

        @Override
        public int hashCode() {
            int result = datasetId != null ? datasetId.hashCode() : 0;
            result = 31 * result + (rowIndex != null ? rowIndex.hashCode() : 0);
            return result;
        }
    }
}
