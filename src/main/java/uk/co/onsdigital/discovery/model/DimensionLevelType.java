package uk.co.onsdigital.discovery.model;

import javax.persistence.*;

/**
 * The persistent class for the dimension_level_type database table.
 */
@Entity
@Table(name = "dimension", indexes = {@Index(columnList = "id")},
        uniqueConstraints=@UniqueConstraint(columnNames={"id"})
)
public class DimensionLevelType {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
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
