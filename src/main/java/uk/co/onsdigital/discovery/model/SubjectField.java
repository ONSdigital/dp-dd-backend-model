package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the subject_field database table.
 */
@Entity
@Table(name = "subject_field")
@NamedQuery(name = "SubjectField.findAll", query = "SELECT s FROM SubjectField s")
public class SubjectField implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "subject_field")
    private String id;

    private String metadata;

    //bi-directional many-to-one association to SubjectField
    @ManyToOne
    @JoinColumn(name = "rel_subject_field")
    private SubjectField subjectField;

    //bi-directional many-to-one association to SubjectField
    @OneToMany(mappedBy = "subjectField")
    private List<SubjectField> subjectFields;

    //bi-directional many-to-many association to ConceptSystem
    @ManyToMany
    @JoinTable(
            name = "subject_field_concept_system"
            , joinColumns = {
            @JoinColumn(name = "subject_field")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "concept_system")
    }
    )
    private List<ConceptSystem> conceptSystems;

    public SubjectField() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public SubjectField getSubjectField() {
        return this.subjectField;
    }

    public void setSubjectField(SubjectField subjectField) {
        this.subjectField = subjectField;
    }

    public List<SubjectField> getSubjectFields() {
        return this.subjectFields;
    }

    public void setSubjectFields(List<SubjectField> subjectFields) {
        this.subjectFields = subjectFields;
    }

    public SubjectField addSubjectField(SubjectField subjectField) {
        getSubjectFields().add(subjectField);
        subjectField.setSubjectField(this);

        return subjectField;
    }

    public SubjectField removeSubjectField(SubjectField subjectField) {
        getSubjectFields().remove(subjectField);
        subjectField.setSubjectField(null);

        return subjectField;
    }

    public List<ConceptSystem> getConceptSystems() {
        return this.conceptSystems;
    }

    public void setConceptSystems(List<ConceptSystem> conceptSystems) {
        this.conceptSystems = conceptSystems;
    }

}