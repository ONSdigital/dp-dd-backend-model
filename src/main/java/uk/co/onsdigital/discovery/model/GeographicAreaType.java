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
 * The persistent class for the geographic_area_type database table.
 */
@Entity
@Table(name = "geographic_area_type")
@NamedQuery(name = GeographicAreaType.FIND_ALL_QUERY, query = "SELECT g FROM GeographicAreaType g")
public class GeographicAreaType implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL_QUERY = "GeographicAreaType.findAll";

    @Id
    @Column(name = "geographic_area_type")
    private String id;

    //bi-directional many-to-one association to GeographicArea
    @OneToMany(mappedBy = "geographicAreaType")
    private List<GeographicArea> geographicAreas;

    public GeographicAreaType() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GeographicArea> getGeographicAreas() {
        return this.geographicAreas;
    }

    public void setGeographicAreas(List<GeographicArea> geographicAreas) {
        this.geographicAreas = geographicAreas;
    }

    public GeographicArea addGeographicArea(GeographicArea geographicArea) {
        getGeographicAreas().add(geographicArea);
        geographicArea.setGeographicAreaType(this);

        return geographicArea;
    }

    public GeographicArea removeGeographicArea(GeographicArea geographicArea) {
        getGeographicAreas().remove(geographicArea);
        geographicArea.setGeographicAreaType(null);

        return geographicArea;
    }

}