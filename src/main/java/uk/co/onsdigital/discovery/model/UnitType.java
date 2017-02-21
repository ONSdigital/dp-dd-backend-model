package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the unit_type database table.
 */
@Entity
@Table(name = "unit_type")
@NamedQuery(name = UnitType.FIND_ALL_QUERY, query = "SELECT u FROM UnitType u")
public class UnitType implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL_QUERY = "UnitType.findAll";

    @Id
    @Column(name = "unit_type")
    private String id;

    //bi-directional many-to-one association to Variable
    @OneToMany(mappedBy = "unitType")
    private List<Variable> variables;

    public UnitType() {
    }

    public UnitType(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Variable> getVariables() {
        return this.variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    public Variable addVariable(Variable variable) {
        getVariables().add(variable);
        variable.setUnitType(this);

        return variable;
    }

    public Variable removeVariable(Variable variable) {
        getVariables().remove(variable);
        variable.setUnitType(null);

        return variable;
    }

}