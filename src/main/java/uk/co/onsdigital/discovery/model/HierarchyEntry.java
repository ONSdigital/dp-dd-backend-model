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

/**
 * The persistent class for the hierarchy_entry database table.
 */
@Entity
@Table(name = "hierarchy_entry", uniqueConstraints=@UniqueConstraint(columnNames={"hierarchy_id", "value_code"}))
@IdClass(HierarchyEntry.HierarchyEntryId.class)
public class HierarchyEntry {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "hierarchy_id")
    private String hierarchyId;

    @Id
    @Column(name = "value_code")
    private String code;

    @Column(name = "value_name")
    private String name;

    @Column(name = "display_order")
    private Integer displayOrder;

    @ManyToOne
    @JoinColumn(name = "level_type")
    private HierarchyLevelType levelType;

    @ManyToOne
    @JoinColumn(name = "hierarchy_id", updatable = false, insertable = false)
    private Hierarchy hierarchy;

    // bi-directional many-to-one relationship defining the hierarchy. This is the owner side.
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "hierarchy_id", referencedColumnName = "hierarchy_id", updatable = false, insertable = false),
            @JoinColumn(name = "parent_code", referencedColumnName = "value_code")})
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

    /**
     * The id fields of a HierarchyEntry.
     */
    static class HierarchyEntryId implements Serializable {
        private String hierarchyId;
        private String code;

        public String getHierarchyId() {
            return hierarchyId;
        }

        public void setHierarchyId(String hierarchyId) {
            this.hierarchyId = hierarchyId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
