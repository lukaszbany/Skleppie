package skleppie.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Produkt musi mieć nazwę")
    @Column(name = "name")
    @NotEmpty(message = "Produkt musi mieć nazwę")
    @Length(max = 255, message = "Zbyt długa nazwa produktu")
    private String name;

    @Column(name = "description")
    @Length(max = 3000, message = "Zbyt długi opis produktu")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "price")
    @NotNull(message = "Produkt musi mieć cenę")
    @Digits(integer = 5, fraction = 2, message = "Nieprawidłowa cena")
    private double price;

    @Column(name = "quantity")
    @NotNull(message = "Określ ilość produktu w magazynie")
    @Digits(integer = 5, fraction = 0, message = "Nieprawidłowa ilość towaru")
    private int quantity;

    @Column(name = "image_filename")
    @Length(max = 255, message = "Zbyt długa nazwa pliku")
    private String imageFilename;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }
}
