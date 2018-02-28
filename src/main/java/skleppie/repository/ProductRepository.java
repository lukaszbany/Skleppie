package skleppie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skleppie.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(int id);
    Product findByName(String name);
    Long removeById(int id);
}
