package skleppie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skleppie.model.Category;
import skleppie.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(int id);
    Product findByName(String name);
    Long removeById(int id);
}
