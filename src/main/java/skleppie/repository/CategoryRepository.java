package skleppie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skleppie.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findById(int id);
    Long removeById(int id);
}
