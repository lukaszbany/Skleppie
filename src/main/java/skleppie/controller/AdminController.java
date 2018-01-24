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
public class AdminController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/categories")
    public ModelAndView categories() {
        ModelAndView modelAndView = new ModelAndView("admin/categories");

        List<Category> categories = categoryService.getCategories();
        Map<Integer, String> nestPrefixes = mapIdWithNestLevelsString(categories);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("nestPrefixes", nestPrefixes);

        return modelAndView;
    }

    @RequestMapping(value = "/categories/add", method = RequestMethod.GET)
    public ModelAndView addCategory(@RequestParam(value = "parentId", required = false)  int parentId) {
        ModelAndView modelAndView = new ModelAndView("admin/edit-category");

        Category category = new Category();
        List<Category> categories = categoryService.getCategories();
        modelAndView.addObject("action", "add");
        modelAndView.addObject("category", category);
        modelAndView.addObject("parentId", parentId);
        modelAndView.addObject("categories", categories);

        return modelAndView;
    }

    @RequestMapping(value = "/categories/add", method = RequestMethod.POST)
    public ModelAndView addCategory(@ModelAttribute("category") @Valid Category category, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/edit-category");
            modelAndView.addObject("action", "add");
            List<Category> categories = categoryService.getCategories();
            modelAndView.addObject("parentId", category.getParent().getId());
            modelAndView.addObject("categories", categories);
        }else {
            categoryService.saveCategory(category);
            modelAndView.addObject("successMessage", "Kategoria została zapisana");
            modelAndView.addObject("backLink", "/admin/categories");
            modelAndView.setViewName("/admin/edit-message");
        }

        return modelAndView;
    }

    @RequestMapping("/categories/remove")
    public ModelAndView removeCategory(@RequestParam("currentId") String currentId) {
        ModelAndView modelAndView = new ModelAndView("/admin/edit-message");
        modelAndView.addObject("backLink", "/admin/categories");

        int id = Integer.parseInt(currentId);

        if(id > 1) {
            categoryService.removeCategory(id);
            modelAndView.addObject("successMessage", "Kategoria została usunięta");
        }else {
            modelAndView.addObject("warningMessage", "Wystąpił błąd. Kategoria nie została usunięta.");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public ModelAndView addCategory(@PathVariable(value = "id") String idString) {
        ModelAndView modelAndView = new ModelAndView("/admin/edit-category");
        modelAndView.addObject("backLink", "/admin/categories");

        int id = Integer.parseInt(idString);

        Category category = categoryService.findCategoryById(id);
        List<Category> categories = categoryService.getCategories();
        modelAndView.addObject("action", id);
        modelAndView.addObject("category", category);
        modelAndView.addObject("parentId", category.getParent().getId());
        modelAndView.addObject("categories", categories);

        return modelAndView;
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.POST)
    public ModelAndView addCategory(@PathVariable(value = "id") String id, @ModelAttribute("category") @Valid Category category, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("/admin/edit-category");
            List<Category> categories = categoryService.getCategories();
            modelAndView.addObject("action", id);
            modelAndView.addObject("parentId", category.getParent().getId());
            modelAndView.addObject("categories", categories);
        }else {
            categoryService.saveCategory(category);
            modelAndView.addObject("successMessage", "Kategoria została zapisana");
            modelAndView.addObject("backLink", "/admin/categories");
            modelAndView.setViewName("/admin/edit-message");
        }

        return modelAndView;
    }

    private Map<Integer, String> mapIdWithNestLevelsString(List<Category> categories) {
        Map<Integer, String> nestPrefixes = new HashMap<>();

        for(Category category : categories) {
            int id = category.getId();
            int nestLevel = calculateNestLevel(category, 0);
            StringBuilder sb = new StringBuilder("|-");
            for(int i = 0; i < nestLevel; i++) {
                sb.append("---");
            }
            String nestPrefix = sb.toString();

            nestPrefixes.put(id, nestPrefix);
        }

        return nestPrefixes;
    }

    private int calculateNestLevel(Category category, int level) {
        Category parent = category.getParent();
        if(parent == null) {
            return level;
        }else {
            return calculateNestLevel(parent, level + 1);
        }
    }
}
