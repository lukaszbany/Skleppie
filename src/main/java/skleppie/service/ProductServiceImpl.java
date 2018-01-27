package skleppie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skleppie.model.Category;
import skleppie.model.Product;
import skleppie.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service("productService")
@Transactional
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
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        List<Product> products = getProducts();
        return products.stream().filter( product -> product.getCategory() == category ).collect(Collectors.toList());
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Long removeProduct(int id) {
        return productRepository.removeById(id);
    }
}
