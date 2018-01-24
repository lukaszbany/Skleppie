package skleppie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skleppie.model.Product;
import skleppie.repository.ProductRepository;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Product findProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
