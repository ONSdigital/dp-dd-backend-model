package uk.co.onsdigital.discovery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the geographic_area database table.
 */
@Entity
@Table(name = "geographic_area", indexes = {@Index(columnList = "ext_code")},
        uniqueConstraints=@UniqueConstraint(columnNames={"ext_code", "geographic_area_hierarchy"})
)
@NamedQuery(name = GeographicArea.FIND_ALL_QUERY, query = "SELECT g FROM GeographicArea g")
public class GeographicArea implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL_QUERY = "GeographicArea.findAll";

    @Id
    @SequenceGenerator(name = "areaseq", sequenceName = "areaseq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "areaseq")
    @Column(name = "geographic_area_id")
    private Long id;

    @Column(name = "ext_code")
    private String extCode;

    private String metadata;

    private String name;

    //bi-directional many-to-one association to GeographicArea
    @ManyToOne
    @JoinColumn(name = "rel_geographic_area_id")
    private GeographicArea geographicArea;

    //bi-directional many-to-one association to GeographicArea
    @OneToMany(mappedBy = "geographicArea")
    private List<GeographicArea> geographicAreas;

    //bi-directional many-to-one association to GeographicAreaHierarchy
    @ManyToOne
    @JoinColumn(name = "geographic_area_hierarchy")
    private GeographicAreaHierarchy geographicAreaHierarchy;

    //bi-directional many-to-one association to GeographicAreaType
    @ManyToOne
    @JoinColumn(name = "geographic_area_type")
    private GeographicAreaType geographicAreaType;

    //bi-directional many-to-one association to GeographicLevelType
    @ManyToOne
    @JoinColumn(name = "geographic_level_type")
    private GeographicLevelType geographicLevelType;

    //bi-directional many-to-one association to Population
    @OneToMany(mappedBy = "geographicArea")
    private List<Population> populations;

    public GeographicArea() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExtCode() {
        return this.extCode;
    }

    public void setExtCode(String extCode) {
        this.extCode = extCode;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeographicArea getGeographicArea() {
        return this.geographicArea;
    }

    public void setGeographicArea(GeographicArea geographicArea) {
        this.geographicArea = geographicArea;
    }

    public List<GeographicArea> getGeographicAreas() {
        return this.geographicAreas;
    }

    public void setGeographicAreas(List<GeographicArea> geographicAreas) {
        this.geographicAreas = geographicAreas;
    }

    public GeographicArea addGeographicArea(GeographicArea geographicArea) {
        getGeographicAreas().add(geographicArea);
        geographicArea.setGeographicArea(this);

        return geographicArea;
    }

    public GeographicArea removeGeographicArea(GeographicArea geographicArea) {
        getGeographicAreas().remove(geographicArea);
        geographicArea.setGeographicArea(null);

        return geographicArea;
    }

    public GeographicAreaHierarchy getGeographicAreaHierarchy() {
        return this.geographicAreaHierarchy;
    }

    public void setGeographicAreaHierarchy(GeographicAreaHierarchy geographicAreaHierarchy) {
        this.geographicAreaHierarchy = geographicAreaHierarchy;
    }

    public GeographicAreaType getGeographicAreaType() {
        return this.geographicAreaType;
    }

    public void setGeographicAreaType(GeographicAreaType geographicAreaType) {
        this.geographicAreaType = geographicAreaType;
    }

    public GeographicLevelType getGeographicLevelType() {
        return this.geographicLevelType;
    }

    public void setGeographicLevelType(GeographicLevelType geographicLevelType) {
        this.geographicLevelType = geographicLevelType;
    }

    public List<Population> getPopulations() {
        return this.populations;
    }

    public void setPopulations(List<Population> populations) {
        this.populations = populations;
    }

    public Population addPopulation(Population population) {
        getPopulations().add(population);
        population.setGeographicArea(this);

        return population;
    }

    public Population removePopulation(Population population) {
        getPopulations().remove(population);
        population.setGeographicArea(null);

        return population;
    }
}