package uk.co.onsdigital.discovery.model;

import javax.persistence.*;

/**
 * The persistent class for the hierarchy_level_type database table.
 */
@Entity
@Table(name = "hierarchy_level_type")
public class HierarchyLevelType {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;

    private Integer level;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
