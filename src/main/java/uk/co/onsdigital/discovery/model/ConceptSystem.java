package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the concept_system database table.
 *
 */
@Entity
@Table(name="concept_system")
@NamedQuery(name="ConceptSystem.findAll", query="SELECT c FROM ConceptSystem c")
public class ConceptSystem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="concept_system", nullable = false)
    private String conceptSystem;

    //bi-directional many-to-one association to Category
    @OneToMany(mappedBy="conceptSystemBean")
    private List<Category> categories;

    //bi-directional many-to-many association to SubjectField
    @ManyToMany(mappedBy="conceptSystems")
    private List<SubjectField> subjectFields;

    public ConceptSystem() {
    }

    public ConceptSystem(String conceptSystem) {
        this.conceptSystem = conceptSystem;
    }

    public String getConceptSystem() {
        return this.conceptSystem;
    }

    public void setConceptSystem(String conceptSystem) {
        this.conceptSystem = conceptSystem;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Category addCategory(Category category) {
        getCategories().add(category);
        category.setConceptSystemBean(this);

        return category;
    }

    public Category removeCategory(Category category) {
        getCategories().remove(category);
        category.setConceptSystemBean(null);

        return category;
    }

    public List<SubjectField> getSubjectFields() {
        return this.subjectFields;
    }

    public void setSubjectFields(List<SubjectField> subjectFields) {
        this.subjectFields = subjectFields;
    }

    @Override
    public boolean equals(Object that) {
        return this == that
                || that instanceof ConceptSystem && Objects.equals(this.conceptSystem, ((ConceptSystem) that).conceptSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conceptSystem);
    }
}