package uk.co.onsdigital.discovery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * The persistent class for the dimensional_data_set database table.
 */
@Entity
@Table(name = "dimensional_data_set",
       indexes = {
        @Index(name = "editions", columnList = "data_resource,major_label,minor_version", unique = false),
        @Index(name = "versions", columnList = "data_resource,major_version,minor_version", unique = true),
       }
)
@NamedQueries({
        @NamedQuery(name = "DimensionalDataSet.findAll", query = "SELECT d FROM DimensionalDataSet d ORDER BY d.s3URL"),
        @NamedQuery(name = "DimensionalDataSet.count", query = "SELECT COUNT(d) FROM DimensionalDataSet d"),
        @NamedQuery(name = DimensionalDataSet.FIND_BY_EDITION_VERSION,
                    query = "SELECT d FROM DimensionalDataSet d WHERE d.major_label = :edition AND d.minor_version = :version ORDER BY d.s3URL"
        )
})
public class DimensionalDataSet implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_BY_EDITION_VERSION = "DimensionalDataSet.findByEditionVersion";

    // The status of a dataset when created, until all rows have been ingested
    public static final String STATUS_NEW = "new";
    // The status of a dataset once all rows have been ingested.
    public static final String STATUS_COMPLETE = "complete";

    @Id
    @Column(name = "dimensional_data_set_id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "authentication_role")
    private String authenticationRole;

    private String distribution;

    private String frequency;

    private String identifier;

    private String issued;

    @Column(name = "json_metadata")
    private String jsonMetadata;

    private String keyword;

    private String landingpage;

    private String language;

    private String license;

    @Column(name = "load_exception")
    private String loadException;

    @Column(columnDefinition = "text")
    private String metadata;

    // add major version label that represents "edition" in our URI path and what we expose as terminology
    @Column(name = "major_label")
    private String majorLabel;

    @Column(name = "major_version", nullable = false)
    private int majorVersion;

    @Column(name = "minor_version", nullable = false)
    private int minorVersion = 0;

    private String modified;

    private Long obscount;

    private String publisher;

    @Column(name = "reference_list")
    private String references;

    @Column(name = "revision_notes")
    private String revisionNotes;

    @Column(name = "revision_reason")
    private String revisionReason;

    private String spatial;

    private String status;

    private String temporal;

    private String theme;

    @Column(name = "title")
    private String title;

    @Column(name = "s3_url")
    private String s3URL;

    @Column(name = "validation_exception")
    private String validationException;

    @Column(name = "validation_message")
    private String validationMessage;

    private String source;

    //bi-directional many-to-one association to DimensionalDataPoint
    @OneToMany(mappedBy = "dimensionalDataSet")
    private List<DimensionalDataPoint> dimensionalDataPoints;

    //bi-directional many-to-one association to DataResource
    @ManyToOne
    @JoinColumn(name = "data_resource")
    private DataResource dataResource;

    //bi-directional many-to-one association to Presentation
    @OneToMany(mappedBy = "dimensionalDataSet")
    private List<Presentation> presentations;

    @ManyToMany
    @JoinTable(name = "dimensional_data_set_concept_system", joinColumns = @JoinColumn(name = "dimensional_data_set_id"),
            inverseJoinColumns = @JoinColumn(name = "concept_system"))
    private Set<ConceptSystem> referencedConceptSystems = new HashSet<>();

    @OneToMany(mappedBy = "dataSet")
    private List<Dimension> dimensions;

    @Column(name = "total_row_count")
    private Long totalRowCount;

    public DimensionalDataSet() {
    }

    public DimensionalDataSet(String s3URL, DataResource dataResource) {
        this.s3URL = s3URL;
        this.dataResource = dataResource;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthenticationRole() {
        return this.authenticationRole;
    }

    public void setAuthenticationRole(String authenticationRole) {
        this.authenticationRole = authenticationRole;
    }

    public String getDistribution() {
        return this.distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIssued() {
        return this.issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getJsonMetadata() {
        return this.jsonMetadata;
    }

    public void setJsonMetadata(String jsonMetadata) {
        this.jsonMetadata = jsonMetadata;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLandingpage() {
        return this.landingpage;
    }

    public void setLandingpage(String landingpage) {
        this.landingpage = landingpage;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLicense() {
        return this.license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLoadException() {
        return this.loadException;
    }

    public void setLoadException(String loadException) {
        this.loadException = loadException;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public int getMajorVersion() {
        return this.majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getMinorVersion() {
        return this.minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public String getModified() {
        return this.modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Long getObscount() {
        return this.obscount;
    }

    public void setObscount(Long obscount) {
        this.obscount = obscount;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getReferences() {
        return this.references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public String getRevisionNotes(String revisionNotes) {
        return this.revisionNotes;
    }

    public void setRevisionNotes(String revisionNotes) {
        this.revisionNotes = revisionNotes;
    }

    public String getRevisionReason(String revisionReason) {
        return this.revisionReason;
    }

    public void setRevisionReason(String revisionReason) {
        this.revisionReason = revisionReason;
    }

    public String getSpatial() {
        return this.spatial;
    }

    public void setSpatial(String spatial) {
        this.spatial = spatial;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTemporal() {
        return this.temporal;
    }

    public void setTemporal(String temporal) {
        this.temporal = temporal;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getS3URL() {
        return this.s3URL;
    }

    public void setS3URL(String s3URL) {
        this.s3URL = s3URL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValidationException() {
        return this.validationException;
    }

    public void setValidationException(String validationException) {
        this.validationException = validationException;
    }

    public String getValidationMessage() {
        return this.validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<DimensionalDataPoint> getDimensionalDataPoints() {
        return this.dimensionalDataPoints;
    }

    public void setDimensionalDataPoints(List<DimensionalDataPoint> dimensionalDataPoints) {
        this.dimensionalDataPoints = dimensionalDataPoints;
    }

    public Long getTotalRowCount() {
        return totalRowCount;
    }

    public void setTotalRowCount(Long totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    public String getMajorLabel() {
        return majorLabel;
    }

    public void setMajorLabel(String majorLabel) {
        this.majorLabel = majorLabel;
    }

    /**
     * Returns a list of all {@link ConceptSystem}s that are referenced by any {@link DimensionalDataPoint} in this
     * data set. These are the <em>dimensions</em> of the data set in terms of the UI.
     *
     * @return the list of all referenced concept systems.
     */
    public Set<ConceptSystem> getReferencedConceptSystems() {
        return referencedConceptSystems;
    }

    public void setReferencedConceptSystems(Set<ConceptSystem> conceptSystems) {
        this.referencedConceptSystems = conceptSystems;
    }

    public void addReferencedConceptSystem(ConceptSystem conceptSystem) {
        if (referencedConceptSystems == null) {
            referencedConceptSystems = new HashSet<>();
        }
        referencedConceptSystems.add(conceptSystem);
    }

    public Stream<GeographicAreaHierarchy> getReferencedGeographies() {
        if (getDimensionalDataPoints() != null) {
            // FIXME: need to populate this during load instead of scanning the entire data point table
            return getDimensionalDataPoints().parallelStream()
                    .unordered()
                    .map(point -> point.getPopulation().getGeographicArea().getGeographicAreaHierarchy())
                    .distinct();
        }
        return Stream.empty();
    }

    public DimensionalDataPoint addDimensionalDataPoint(DimensionalDataPoint dimensionalDataPoint) {
        getDimensionalDataPoints().add(dimensionalDataPoint);
        dimensionalDataPoint.setDimensionalDataSet(this);

        return dimensionalDataPoint;
    }

    public DimensionalDataPoint removeDimensionalDataPoint(DimensionalDataPoint dimensionalDataPoint) {
        getDimensionalDataPoints().remove(dimensionalDataPoint);
        dimensionalDataPoint.setDimensionalDataSet(null);

        return dimensionalDataPoint;
    }

    public DataResource getDataResource() {
        return this.dataResource;
    }

    public void setDataResource(DataResource dataResource) {
        this.dataResource = dataResource;
    }

    public List<Presentation> getPresentations() {
        return this.presentations;
    }

    public void setPresentations(List<Presentation> presentations) {
        this.presentations = presentations;
    }

    public Presentation addPresentation(Presentation presentation) {
        getPresentations().add(presentation);
        presentation.setDimensionalDataSet(this);

        return presentation;
    }

    public Presentation removePresentation(Presentation presentation) {
        getPresentations().remove(presentation);
        presentation.setDimensionalDataSet(null);

        return presentation;
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public String toString() {
        return "DimensionalDataSet{" +
                "dimensionalDataSetId=" + id +
                ", majorVersion=" + majorVersion +
                ", minorVersion=" + minorVersion +
                ", title='" + title + '\'' +
                ", s3URL='" + s3URL + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}