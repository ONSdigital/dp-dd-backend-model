package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * The persistent class for the hierarchy_entry database table.
 */
@Entity
@Table(name = "hierarchy_entry", uniqueConstraints = @UniqueConstraint(columnNames={"hierarchy_id", "code"}))
public class HierarchyEntry {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String code;

    private String name;

    @Column(name = "display_order")
    private Integer displayOrder;

    @ManyToOne
    @JoinColumn(name = "hierarchy_level_type_id")
    private HierarchyLevelType levelType;

    @ManyToOne
    @JoinColumn(name = "hierarchy_id", nullable = false)
    private Hierarchy hierarchy;

    // bi-directional many-to-one relationship defining the trees structure. This is the owner side.
    @ManyToOne
    @JoinColumn(name = "parent", referencedColumnName = "id")
    private HierarchyEntry parent;

    // bi-directional one-to-many relationship defining the hierarchy. Owned by the children.
    @OneToMany(mappedBy = "parent")
    private List<HierarchyEntry> children;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    public HierarchyLevelType getLevelType() {
        return levelType;
    }

    public void setLevelType(HierarchyLevelType levelType) {
        this.levelType = levelType;
    }

    public HierarchyEntry getParent() {
        return parent;
    }

    public void setParent(HierarchyEntry parent) {
        this.parent = parent;
    }

    public List<HierarchyEntry> getChildren() {
        return children;
    }

    public void setChildren(List<HierarchyEntry> children) {
        this.children = children;
    }

}
