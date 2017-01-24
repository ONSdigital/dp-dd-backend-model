package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the dimension database table.
 */
@Entity
@Table(name = "dimension")
public class Dimension {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "dimension_id")
    private String id;

    @Column(name = "dimension_name")
    private String name;

    @Column(name = "dimension_type")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
