package skleppie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skleppie.model.Category;
import skleppie.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        Category category = findCategoryById(1);
        categories.add(category);
        categories.addAll(addSubcategories(category));

        return categories;
    }

    @Override
    public Long removeCategory(int id) {
        return categoryRepository.removeById(id);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    private List<Category> addSubcategories(Category parent) {
        List<Category> categories = new ArrayList<>();
        List<Category> subcategoriesOfParent = new ArrayList<>(parent.getSubcategories());
        subcategoriesOfParent.sort( Comparator.comparingInt( c -> c.getId() ));
        for(Category category : subcategoriesOfParent) {
            categories.add(category);
            categories.addAll(addSubcategories(category));
        }

        return categories;
    }
}
