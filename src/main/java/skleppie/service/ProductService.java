package skleppie.service;

import skleppie.model.Product;

public interface ProductService {
    Product findProductById(int id);
    Product findProductByName(String name);
    Product saveProduct(Product product);
}
