package skleppie.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import skleppie.model.Category;
import skleppie.service.CategoryService;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CategoryFormatter implements Formatter<Category> {

    @Autowired
    private CategoryService categoryService;

    @Override
    public Category parse(String categoryId, Locale locale) throws ParseException {
        return categoryService.findCategoryById(Integer.parseInt(categoryId));
    }

    @Override
    public String print(Category category, Locale locale) {
        return category.getName();
    }
}
