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
 * The persistent class for the value_domain database table.
 */
@Entity
@Table(name = "value_domain")
@NamedQuery(name = "ValueDomain.findAll", query = "SELECT v FROM ValueDomain v")
public class ValueDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "value_domain")
    private String id;

    //bi-directional many-to-one association to Variable
    @OneToMany(mappedBy = "valueDomain")
    private List<Variable> variables;

    public ValueDomain() {
    }

    public ValueDomain(String id) {
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
        variable.setValueDomain(this);

        return variable;
    }

    public Variable removeVariable(Variable variable) {
        getVariables().remove(variable);
        variable.setValueDomain(null);

        return variable;
    }

}