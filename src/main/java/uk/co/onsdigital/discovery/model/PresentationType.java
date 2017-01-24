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
 * The persistent class for the presentation_type database table.
 */
@Entity
@Table(name = "presentation_type")
@NamedQuery(name = "PresentationType.findAll", query = "SELECT p FROM PresentationType p")
public class PresentationType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "presentation_type")
    private String id;

    //bi-directional many-to-one association to Presentation
    @OneToMany(mappedBy = "presentationType")
    private List<Presentation> presentations;

    public PresentationType() {
    }

    public PresentationType(String presentationType) {
        super();
        this.setId(presentationType);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Presentation> getPresentations() {
        return this.presentations;
    }

    public void setPresentations(List<Presentation> presentations) {
        this.presentations = presentations;
    }

    public Presentation addPresentation(Presentation presentation) {
        getPresentations().add(presentation);
        presentation.setPresentationType(this);

        return presentation;
    }

    public Presentation removePresentation(Presentation presentation) {
        getPresentations().remove(presentation);
        presentation.setPresentationType(null);

        return presentation;
    }

}