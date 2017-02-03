package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the hierarchy database table.
 */
@Entity
@Table(name = "hierarchy")
public class Hierarchy {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;

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

    @Override
    public String toString() {
        return "Hierarchy{" +
                "id='" + id + '\'' +
                '}';
    }
}
