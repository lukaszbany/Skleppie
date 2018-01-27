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

    @RequestMapping("/products")
    public ModelAndView productsPanel(@RequestParam(name = "category", required = false) String categoryId) {
        ModelAndView modelAndView = new ModelAndView("admin/products");
        Category currentCategory = getCurrentCategory(categoryId);
        List<Product> products = getFilteredCategories(currentCategory);
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


    private List<Product> getFilteredCategories(Category category) {
        if(category == null) {
            return productService.getProducts();
        }

        return productService.getProductsByCategory(category);
    }


    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public ModelAndView addProduct(@Valid Product product, BindingResult bindingResult) {
        ModelAndView modelAndView;

        if(bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("admin/edit-product");
            List<Category> categories = categoryService.getCategories();
            List<String> images = getFilenamesOfImages();
            modelAndView.addObject("action", "add");
            modelAndView.addObject("categories", categories);
            modelAndView.addObject("product", product);
            modelAndView.addObject("images", images);
        }else {
            productService.saveProduct(product);
            modelAndView = new ModelAndView("/admin/edit-message");
            modelAndView.addObject("successMessage", "Produkt został zapisany");
            modelAndView.addObject("backLink", "/admin/products");
        }

        return modelAndView;
    }

    private List<String> getFilenamesOfImages() {
        List<String> images = new ArrayList<>();

            File imageDirectory = new File("src/main/resources/static/public/images/products");
            if(imageDirectory.exists()) {
                for(File currentFile : imageDirectory.listFiles()) {
                    images.add(currentFile.getName());
                }
            }

        return images;
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public ModelAndView addProduct() {
        ModelAndView modelAndView = new ModelAndView("admin/edit-product");

        List<Category> categories = categoryService.getCategories();
        List<String> images = getFilenamesOfImages();
        Product product = new Product();

        modelAndView.addObject("action", "add");
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("images", images);
        modelAndView.addObject("product", product);

        return modelAndView;
    }


    @RequestMapping(value = "/products/{id}", method = RequestMethod.POST)
    public ModelAndView modifyProduct(@PathVariable(value = "id") String idString, @Valid Product product, BindingResult bindingResult) {
        ModelAndView modelAndView;

        if(bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("admin/edit-product");
            List<Category> categories = categoryService.getCategories();
            List<String> images = getFilenamesOfImages();
            Category currentCategory = product.getCategory();
            int id = Integer.parseInt(idString);
            modelAndView.addObject("action", id);
            modelAndView.addObject("categories", categories);
            modelAndView.addObject("images", images);
            modelAndView.addObject("product", product);
            modelAndView.addObject("currentCategory", currentCategory);
        }else {
            productService.saveProduct(product);
            modelAndView = new ModelAndView("/admin/edit-message");
            modelAndView.addObject("successMessage", "Produkt został pomyślnie zmieniony");
            modelAndView.addObject("backLink", "/admin/products");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ModelAndView modifyProduct(@PathVariable(value = "id") String idString) {
        ModelAndView modelAndView = new ModelAndView("admin/edit-product");
        int id = Integer.parseInt(idString);
        Product currentProduct = productService.findProductById(id);
        List<Category> categories = categoryService.getCategories();
        List<String> images = getFilenamesOfImages();
        Category currentCategory = currentProduct.getCategory();

        modelAndView.addObject("action", id);
        modelAndView.addObject("currentCategory", currentCategory);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("images", images);
        modelAndView.addObject("product", currentProduct);

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
