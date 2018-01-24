package skleppie.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull(message = "Kategoria musi mieć nazwę")
    @NotEmpty(message = "Kategoria musi mieć nazwę")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Category> subcategories;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private Set<Product> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Set<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
