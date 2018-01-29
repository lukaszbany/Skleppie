package skleppie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import skleppie.model.Category;
import skleppie.model.Product;
import skleppie.service.CategoryService;
import skleppie.service.ProductPictureService;
import skleppie.service.ProductService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductPictureService productPictureService;

    @RequestMapping("/products")
    public ModelAndView productsPanel(@RequestParam(name = "category", required = false) String categoryId) {
        ModelAndView modelAndView = new ModelAndView("admin/products");

        addProductsFilteredByCategory(modelAndView, categoryId);
        addCategories(modelAndView);

        return modelAndView;
    }

    private void addProductsFilteredByCategory(ModelAndView modelAndView, String categoryId) {
        Category currentCategory = getCurrentCategory(categoryId);
        modelAndView.addObject("currentCategory", currentCategory);

        List<Product> products = getFilteredCategories(currentCategory);
        modelAndView.addObject("products", products);
    }

    private Category getCurrentCategory(String categoryId) {
        if (categoryId == null) {
            return null;
        }

        int id = Integer.parseInt(categoryId);
        return categoryService.findCategoryById(id);
    }


    private List<Product> getFilteredCategories(Category category) {
        if (category == null) {
            return productService.getProducts();
        }

        return productService.getProductsByCategory(category);
    }


    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public ModelAndView addProduct(@Valid Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            prepareEditPageForErrors(modelAndView, product);
            modelAndView.addObject("action", "add");

        } else {
            productService.saveProduct(product);
            prepareSuccessPage(modelAndView);
        }

        return modelAndView;
    }


    private void prepareEditPage(ModelAndView modelAndView) {
        modelAndView.setViewName("admin/edit-product");

        addCategories(modelAndView);
        addProductImages(modelAndView);
    }

    private void addCategories(ModelAndView modelAndView) {
        List<Category> categories = categoryService.getCategories(1);
        modelAndView.addObject("categories", categories);
    }

    private void addProductImages(ModelAndView modelAndView) {
        List<String> images = productPictureService.getFilenamesOfImages();
        modelAndView.addObject("images", images);
    }

    private void prepareEditPageForErrors(ModelAndView modelAndView, Product product) {
        prepareEditPage(modelAndView);
        addCategoryOfCurrentProduct(modelAndView, product);
        modelAndView.addObject("product", product);
    }

    private void addCategoryOfCurrentProduct(ModelAndView modelAndView, Product product) {
        Category currentCategory = product.getCategory();
        modelAndView.addObject("currentCategory", currentCategory);
    }

    private void prepareSuccessPage(ModelAndView modelAndView) {
        modelAndView.setViewName("/admin/edit-message");
        modelAndView.addObject("successMessage", "Produkt został pomyślnie zmieniony");
        modelAndView.addObject("backLink", "/admin/products");
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public ModelAndView addProduct() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("action", "add");

        prepareEditPage(modelAndView);
        addNewProduct(modelAndView);

        return modelAndView;
    }

    private void addNewProduct(ModelAndView modelAndView) {
        Product product = new Product();
        modelAndView.addObject("product", product);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.POST)
    public ModelAndView modifyProduct(@PathVariable(value = "id") String idString, @Valid Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            prepareEditPageForErrors(modelAndView, product);
            modelAndView.addObject("action", Integer.parseInt(idString));
        } else {
            productService.saveProduct(product);
            prepareSuccessPage(modelAndView);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ModelAndView modifyProduct(@PathVariable(value = "id") String idString) {
        ModelAndView modelAndView = new ModelAndView();
        prepareEditPage(modelAndView);

        int id = Integer.parseInt(idString);
        modelAndView.addObject("action", id);

        Product currentProduct = productService.findProductById(id);
        modelAndView.addObject("product", currentProduct);

        addCategoryOfCurrentProduct(modelAndView, currentProduct);

        return modelAndView;
    }

    @RequestMapping("/products/remove")
    public ModelAndView removeProduct(@RequestParam("currentId") String currentId) {

        int id = Integer.parseInt(currentId);
        productService.removeProduct(id);
        ModelAndView modelAndView = new ModelAndView("/admin/edit-message");
        modelAndView.addObject("successMessage", "Produkt został pomyślnie usunięty");
        modelAndView.addObject("backLink", "/admin/products");

        return modelAndView;
    }

}
