package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the geographic_level_type database table.
 */
@Entity
@Table(name = "geographic_level_type")
@NamedQuery(name = GeographicLevelType.FIND_ALL_QUERY, query = "SELECT g FROM GeographicLevelType g")
public class GeographicLevelType implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL_QUERY = "GeographicLevelType.findAll";

    @Id
    @Column(name = "geographic_level_type")
    private String id;

    private String metadata;

    //bi-directional many-to-one association to GeographicArea
    @OneToMany(mappedBy = "geographicLevelType")
    private List<GeographicArea> geographicAreas;

    public GeographicLevelType() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public List<GeographicArea> getGeographicAreas() {
        return this.geographicAreas;
    }

    public void setGeographicAreas(List<GeographicArea> geographicAreas) {
        this.geographicAreas = geographicAreas;
    }

    public GeographicArea addGeographicArea(GeographicArea geographicArea) {
        getGeographicAreas().add(geographicArea);
        geographicArea.setGeographicLevelType(this);

        return geographicArea;
    }

    public GeographicArea removeGeographicArea(GeographicArea geographicArea) {
        getGeographicAreas().remove(geographicArea);
        geographicArea.setGeographicLevelType(null);

        return geographicArea;
    }

}