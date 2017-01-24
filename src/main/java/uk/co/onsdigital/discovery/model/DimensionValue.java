package uk.co.onsdigital.discovery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the dimension_level_type database table.
 */
@Entity
@Table(name = "dimension_value", uniqueConstraints=@UniqueConstraint(columnNames={"dimension_id", "value_code"}))
@IdClass(DimensionValue.DimensionValueId.class)
public class DimensionValue {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "dimension_id")
    private String dimensionId;

    @Id
    @Column(name = "value_code")
    private String code;

    @Column(name = "value_name")
    private String name;

    @Column(name = "display_order")
    private Integer displayOrder;

    @ManyToOne
    @JoinColumn(name = "level_type")
    private DimensionLevelType levelType;

    @ManyToOne
    @JoinColumn(name = "dimension_id", updatable = false, insertable = false)
    private Dimension dimension;

    // bi-directional many-to-one relationship defining the hierarchy. This is the owner side.
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dimension_id", referencedColumnName = "dimension_id", updatable = false, insertable = false),
            @JoinColumn(name = "parent_code", referencedColumnName = "value_code")})
    private DimensionValue parent;

    // bi-directional one-to-many relationship defining the hierarchy. Owned by the children.
    @OneToMany(mappedBy = "parent")
    private List<DimensionValue> children;

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

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public DimensionLevelType getLevelType() {
        return levelType;
    }

    public void setLevelType(DimensionLevelType levelType) {
        this.levelType = levelType;
    }

    public DimensionValue getParent() {
        return parent;
    }

    public void setParent(DimensionValue parent) {
        this.parent = parent;
    }

    public List<DimensionValue> getChildren() {
        return children;
    }

    public void setChildren(List<DimensionValue> children) {
        this.children = children;
    }

    /**
     * The id fields of a DimensionValue.
     */
    static class DimensionValueId implements Serializable {
        private String dimensionId;
        private String code;

        public String getDimensionId() {
            return dimensionId;
        }

        public void setDimensionId(String dimensionId) {
            this.dimensionId = dimensionId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
