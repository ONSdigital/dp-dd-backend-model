package uk.co.onsdigital.discovery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * The persistent class for the dimensional_data_set database table.
 */
@Entity
@Table(name = "data_set",
       indexes = {
        @Index(name = "editions", columnList = "data_resource,major_label,minor_version", unique = false),
        @Index(name = "versions", columnList = "data_resource,major_version,minor_version", unique = true),
       }
)
@NamedQueries({
        @NamedQuery(name = DataSet.FIND_ALL_QUERY, query = "SELECT d FROM DataSet d ORDER BY d.s3URL"),
        @NamedQuery(name = DataSet.COUNT_QUERY, query = "SELECT COUNT(d) FROM DataSet d"),
        @NamedQuery(name = DataSet.FIND_BY_EDITION_VERSION,
                    query = "SELECT d FROM DataSet d " +
                            "WHERE d.majorLabel = :edition AND d.minorVersion = :version AND d.dataResource.id = :dataResource ORDER BY d.s3URL"),
        @NamedQuery(name = DataSet.FIND_BY_ID, query = "SELECT d FROM DataSet d WHERE d.id = :id"),
        @NamedQuery(name = DataSet.LOOKUP_S3_URL, query = "SELECT d.s3URL FROM DataSet d WHERE d.id = :id")
})
public class DataSet implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL_QUERY = "DimensionalDataSet.findAll";
    public static final String COUNT_QUERY = "DimensionalDataSet.count";
    public static final String FIND_BY_EDITION_VERSION = "DimensionalDataSet.findByEditionVersion";
    public static final String FIND_BY_ID = "DimensionalDataSet.findById";
    public static final String LOOKUP_S3_URL = "DimensionalDataSet.lookupS3Url";
    public static final String EDITION_PARAM = "edition";
    public static final String VERSION_PARAM = "version";
    public static final String DATA_RESOURCE_PARAM = "dataResource";
    public static final String ID_PARAM = "id";

    // The status of a dataset when created, until all rows have been ingested
    public static final String STATUS_NEW = "new";
    // The status of a dataset once all rows have been ingested.
    public static final String STATUS_COMPLETE = "complete";

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(columnDefinition = "text")
    private String metadata;

    // add major version label that represents "edition" in our URI path and what we expose as terminology
    @Column(name = "major_label")
    private String majorLabel;

    @Column(name = "major_version", nullable = false)
    private int majorVersion;

    @Column(name = "minor_version", nullable = false)
    private int minorVersion = 0;

    @Column(name = "revision_notes")
    private String revisionNotes;

    @Column(name = "revision_reason")
    private String revisionReason;

    private String status;

    @Column(name = "title")
    private String title;

    @Column(name = "s3_url")
    private String s3URL;

    //bi-directional many-to-one association to DataResource
    @ManyToOne
    @JoinColumn(name = "data_resource")
    private DataResource dataResource;

    @OneToMany(mappedBy = "dataSet")
    private List<Dimension> dimensions;

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getMajorLabel() {
        return majorLabel;
    }

    public void setMajorLabel(String majorLabel) {
        this.majorLabel = majorLabel;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public String getRevisionNotes() {
        return revisionNotes;
    }

    public void setRevisionNotes(String revisionNotes) {
        this.revisionNotes = revisionNotes;
    }

    public String getRevisionReason() {
        return revisionReason;
    }

    public void setRevisionReason(String revisionReason) {
        this.revisionReason = revisionReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getS3URL() {
        return s3URL;
    }

    public void setS3URL(String s3URL) {
        this.s3URL = s3URL;
    }

    public DataResource getDataResource() {
        return dataResource;
    }

    public Long getTotalRowCount() {
        return totalRowCount;
    }

    public void setTotalRowCount(Long totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    @Column(name = "total_row_count")
    private Long totalRowCount;

    public DataSet() {
    }

    public DataSet(String s3URL, DataResource dataResource) {
        this.s3URL = s3URL;
        this.dataResource = dataResource;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public void setDataResource(DataResource dataResource) {
        this.dataResource = dataResource;
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public String toString() {
        return "DataSet{" +
                "id=" + id +
                ", majorVersion=" + majorVersion +
                ", minorVersion=" + minorVersion +
                ", title='" + title + '\'' +
                ", s3URL='" + s3URL + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}