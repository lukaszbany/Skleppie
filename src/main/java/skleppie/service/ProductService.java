package skleppie.service;

import skleppie.model.Category;
import skleppie.model.Product;

import java.util.List;

public interface ProductService {
    Product findProductById(int id);
    Product findProductByName(String name);
    List<Product> getProducts();
    List<Product> getProductsByCategory(Category category);
    Product saveProduct(Product product);
    Long removeProduct(int id);
}
