package skleppie.service;

import skleppie.model.Category;

import java.util.List;

public interface CategoryService {
    Category findCategoryById(int id);
    List<Category> getCategories();
    Category saveCategory(Category category);
    Long removeCategory(int id);
}
