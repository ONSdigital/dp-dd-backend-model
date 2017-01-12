package uk.co.onsdigital.discovery.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the category database table.
 *
 */
@Entity
@NamedQueries({
        @NamedQuery(name="Category.findAll", query="SELECT c FROM Category c"),
        @NamedQuery(name="Category.findByNameAndConceptSystem",
                query = "SELECT c from Category c WHERE c.name = :categoryName AND c.conceptSystemBean.conceptSystem = :conceptSystem")
})
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "catseq", sequenceName = "catseq", allocationSize = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catseq")
    @Column(name = "category_id")
    private Long categoryId;

    private String name;

    //bi-directional many-to-one association to Category
    @ManyToOne
    @JoinColumn(name = "fk_category_id")
    private Category category;

    //bi-directional many-to-one association to Category
    @OneToMany(mappedBy = "category")
    private List<Category> categories;

    //bi-directional many-to-one association to ConceptSystem
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "concept_system")
    private ConceptSystem conceptSystemBean;

    //bi-directional many-to-many association to Variable
    @ManyToMany(mappedBy = "categories")
    private List<Variable> variables;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Category addCategory(Category category) {
        getCategories().add(category);
        category.setCategory(this);

        return category;
    }

    public Category removeCategory(Category category) {
        getCategories().remove(category);
        category.setCategory(null);

        return category;
    }

    public ConceptSystem getConceptSystemBean() {
        return this.conceptSystemBean;
    }

    public void setConceptSystemBean(ConceptSystem conceptSystemBean) {
        this.conceptSystemBean = conceptSystemBean;
    }

    public List<Variable> getVariables() {
        return this.variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

}