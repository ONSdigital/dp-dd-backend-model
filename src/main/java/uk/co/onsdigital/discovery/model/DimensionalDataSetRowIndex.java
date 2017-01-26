package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "dimensional_data_set_row_index")
@NamedQueries({
        @NamedQuery(name = "DimensionalDataSetRowIndex.countForDataset", query = "SELECT COUNT(r) FROM DimensionalDataSetRowIndex r WHERE r.datasetId = :datasetId"),
        @NamedQuery(name = "DimensionalDataSetRowIndex.deleteForDataset", query = "DELETE FROM DimensionalDataSetRowIndex r WHERE r.datasetId = :datasetId")
})
public class DimensionalDataSetRowIndex {
    @Id
    @Column(name = "dimensional_data_set_id", columnDefinition = "uuid")
    private UUID datasetId;

    @Id
    @Column(name = "row_index")
    private Long rowIndex;

}
