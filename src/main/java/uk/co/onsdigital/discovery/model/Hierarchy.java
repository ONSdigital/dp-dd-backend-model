package uk.co.onsdigital.discovery.model;

import javax.persistence.*;

/**
 * The persistent class for the hierarchy database table.
 */
@Entity
@Table(name = "hierarchy")
@NamedQueries({
        @NamedQuery(name = Hierarchy.FIND_ALL, query = "SELECT h FROM Hierarchy h ORDER BY h.name")
})
public class Hierarchy {

    public static final String FIND_ALL = "Hierarchy.findAll";

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
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
