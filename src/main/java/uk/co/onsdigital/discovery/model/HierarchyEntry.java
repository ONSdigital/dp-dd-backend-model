package uk.co.onsdigital.discovery.model;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * The persistent class for the hierarchy_entry database table.
 */
@Entity
@Table(name = "hierarchy_entry", uniqueConstraints = @UniqueConstraint(columnNames={"hierarchy_id", "code"}))
@NamedQueries({
        @NamedQuery(name = HierarchyEntry.FIND_QUERY, query = "SELECT he FROM HierarchyEntry he where he.hierarchy.id = :hierarchyId and he.code = :code"),
        @NamedQuery(name = HierarchyEntry.FIND_BY_HIERARCHY_ID, query = "SELECT he FROM HierarchyEntry he WHERE he.hierarchy.id = :hierarchyId ORDER BY he.displayOrder")
})
public class HierarchyEntry {

    private static final long serialVersionUID = 1L;

    /** Named query to find an entry by hierarchy id and code. */
    public static final String FIND_QUERY = "HierarchyEntry.findByHierarchyAndCode";

    /** Named query to find all entries in a given hierarchy. */
    public static final String FIND_BY_HIERARCHY_ID = "HierarchyEntry.findByHierarchy";

    /** Query param specifying the hierarchy id. */
    public static final String HIERARCHY_ID_PARAM = "hierarchyId";
    /** Query param specifying the code of the hierarchy entry. */
    public static final String CODE_PARAM = "code";

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
    @JoinFetch(JoinFetchType.OUTER)
    private HierarchyLevelType levelType;

    @ManyToOne
    @JoinColumn(name = "hierarchy_id", nullable = false)
    private Hierarchy hierarchy;

    // bi-directional many-to-one relationship defining the trees structure. This is the owner side.
    @ManyToOne
    @JoinColumn(name = "parent", referencedColumnName = "id")
    @JoinFetch(JoinFetchType.OUTER)
    private HierarchyEntry parent;

    // bi-directional one-to-many relationship defining the hierarchy. Owned by the children.
    @OneToMany(mappedBy = "parent")
    private List<HierarchyEntry> children;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "HierarchyEntry{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", hierarchy='" + hierarchy + '\'' +
                '}';
    }
}
