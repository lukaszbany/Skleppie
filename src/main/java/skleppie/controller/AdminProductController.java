package skleppie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import skleppie.model.Category;
import skleppie.model.Product;
import skleppie.service.CategoryService;
import skleppie.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminProductController {

    public static final int LENGTH_OF_SHORT_DESCRIPTION = 100;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/products")
    public ModelAndView productsPanel(@RequestParam(name = "category", required = false) String categoryId) {
        ModelAndView modelAndView = new ModelAndView("admin/products");
        Category currentCategory = getCurrentCategory(categoryId);
        List<Product> products = getProductsWithShortenedDescription(currentCategory);
        List<Category> categories = categoryService.getCategories();

        modelAndView.addObject("products", products);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("currentCategory", currentCategory);

        return modelAndView;
    }

    private Category getCurrentCategory(String categoryId) {
        if(categoryId == null) {
            return null;
        }

        int id = Integer.parseInt(categoryId);
        return categoryService.findCategoryById(id);
    }

    private List<Product> getProductsWithShortenedDescription(Category category) {
        List<Product> products = getFilteredCategories(category);
        for(Product product : products) {
            shortenDescriptionOfProduct(product);
        }

        return products;
    }

    private List<Product> getFilteredCategories(Category category) {
        if(category == null) {
            return productService.getProducts();
        }

        return productService.getProductsByCategory(category);
    }

    private void shortenDescriptionOfProduct(Product product) {
        String description = product.getDescription();
        description = description.substring(0, LENGTH_OF_SHORT_DESCRIPTION) + "...";
        product.setDescription(description);
    }

}
