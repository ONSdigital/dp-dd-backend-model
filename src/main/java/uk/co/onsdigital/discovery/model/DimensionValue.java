package uk.co.onsdigital.discovery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the dimension_level_type database table.
 */
@Entity
@Table(name = "dimension", indexes = {@Index(columnList = "id")},
        uniqueConstraints=@UniqueConstraint(columnNames={"id"})
)
@IdClass(DimensionValue.DimensionValueId.class)
public class DimensionValue {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "dimension_id")
    private String dimensionId;

    @Id
    @Column(name = "value_code")
    private String code;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "value_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "dimension_id")
    private Dimension dimension;

    @ManyToOne
    @JoinColumn(name = "level_type")
    private DimensionLevelType levelType;

    // bi-directional many-to-one relationship defining the hierarchy. This is the owner side.
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "dimension_id", referencedColumnName = "dimension_id"),
            @JoinColumn(name = "parent_code", referencedColumnName = "value_code")})
    private DimensionValue parent;

    // bi-directional one-to-many relationship defining the hierarchy. Owned by the children.
    @OneToMany(mappedBy = "parent")
    private List<DimensionValue> children;

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
