package skleppie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import skleppie.model.Category;
import skleppie.service.CategoryService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminCategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/categories")
    public ModelAndView categories() {
        ModelAndView modelAndView = new ModelAndView("admin/categories");
        addCategoriesAndPrefixesForNestLevel(modelAndView);

        return modelAndView;
    }

    private void addCategoriesAndPrefixesForNestLevel(ModelAndView modelAndView) {
        List<Category> categories = addCategories(modelAndView);
        Map<Integer, String> nestPrefixes = mapIdWithNestLevelsString(categories);
        modelAndView.addObject("nestPrefixes", nestPrefixes);
    }

    @RequestMapping(value = "/categories/add", method = RequestMethod.GET)
    public ModelAndView addCategory(@RequestParam(value = "parentId", required = false) int parentId) {
        ModelAndView modelAndView = new ModelAndView("admin/edit-category");
        modelAndView.addObject("action", "add");
        modelAndView.addObject("parentId", parentId);

        addCategories(modelAndView);
        addNewCategory(modelAndView);

        return modelAndView;
    }

    private List<Category> addCategories(ModelAndView modelAndView) {
        List<Category> categories = categoryService.getCategories(1);
        modelAndView.addObject("categories", categories);

        return categories;
    }

    private void addNewCategory(ModelAndView modelAndView) {
        Category category = new Category();
        modelAndView.addObject("category", category);
    }

    @RequestMapping(value = "/categories/add", method = RequestMethod.POST)
    public ModelAndView addCategory(@ModelAttribute("category") @Valid Category category, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            configureAddPageForErrors(modelAndView, category);
        } else {
            categoryService.saveCategory(category);
            configurePageForSuccess(modelAndView);
        }

        return modelAndView;
    }

    private void configureAddPageForErrors(ModelAndView modelAndView, Category category) {
        modelAndView.addObject("action", "add");
        configurePageForErrors(modelAndView, category);
    }

    private void configurePageForErrors(ModelAndView modelAndView, Category category) {
        modelAndView.setViewName("admin/edit-category");
        modelAndView.addObject("parentId", category.getParent().getId());
        addCategories(modelAndView);
    }

    private void configurePageForSuccess(ModelAndView modelAndView) {
        modelAndView.addObject("successMessage", "Kategoria została zapisana");
        modelAndView.addObject("backLink", "/admin/categories");
        modelAndView.setViewName("/admin/edit-message");
    }


    @RequestMapping("/categories/remove")
    public ModelAndView removeCategory(@RequestParam("currentId") String currentId) {
        ModelAndView modelAndView = new ModelAndView("/admin/edit-message");
        modelAndView.addObject("backLink", "/admin/categories");

        int id = Integer.parseInt(currentId);

        if (id > 1) {
            categoryService.removeCategory(id);
            modelAndView.addObject("successMessage", "Kategoria została usunięta");
        } else {
            modelAndView.addObject("warningMessage", "Wystąpił błąd. Kategoria nie została usunięta.");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public ModelAndView addCategory(@PathVariable(value = "id") String idString) {
        ModelAndView modelAndView = new ModelAndView();
        prepareEditPage(modelAndView, idString);
        addCategories(modelAndView);

        return modelAndView;
    }

    private void prepareEditPage(ModelAndView modelAndView, String idString) {
        modelAndView.setViewName("/admin/edit-category");
        int id = Integer.parseInt(idString);
        Category category = categoryService.findCategoryById(id);
        modelAndView.addObject("action", id);
        modelAndView.addObject("category", category);
        modelAndView.addObject("parentId", category.getParent().getId());
    }


    @RequestMapping(value = "/categories/{id}", method = RequestMethod.POST)
    public ModelAndView addCategory(@PathVariable(value = "id") String id, @ModelAttribute("category") @Valid Category category, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            configureEditPageForErrors(modelAndView, category, id);
            addCategories(modelAndView);
        } else {
            categoryService.saveCategory(category);
            configurePageForSuccess(modelAndView);
        }

        return modelAndView;
    }

    private void configureEditPageForErrors(ModelAndView modelAndView, Category category, String id) {
        modelAndView.addObject("action", id);
        configurePageForErrors(modelAndView, category);
    }

    private Map<Integer, String> mapIdWithNestLevelsString(List<Category> categories) {
        Map<Integer, String> nestPrefixes = new HashMap<>();

        for (Category category : categories) {
            preparePrefix(nestPrefixes, category);
        }

        return nestPrefixes;
    }

    private void preparePrefix(Map<Integer, String> nestPrefixes, Category category) {
        int id = category.getId();
        int nestLevel = calculateNestLevel(category, 0);

        StringBuilder sb = new StringBuilder("|-");
        for (int i = 0; i < nestLevel; i++) {
            sb.append("---");
        }

        String nestPrefix = sb.toString();
        nestPrefixes.put(id, nestPrefix);
    }

    private int calculateNestLevel(Category category, int level) {
        Category parent = category.getParent();
        if (parent == null) {
            return level;
        } else {
            return calculateNestLevel(parent, level + 1);
        }
    }
}
