package com.haimp02.pointofsales.controllers;

import com.haimp02.pointofsales.models.entities.Category;
import com.haimp02.pointofsales.services.interfaces.CategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {
    
    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String index(Model model, @RequestParam(required=false) Integer page, @RequestParam(required=false) String search){
        if (page == null || page<1) {
            page = 1;
        }
        page--;
        if (search == null) {
            search = "";
        } else {
            model.addAttribute("search", search);
        }
        Page<Category> categories = categoryService.findAll(page, search);
        model.addAttribute("categories", categories);
        model.addAttribute("page", page);
        model.addAttribute("element", categories.getContent().size());
        return "categories/index";
    }

    @GetMapping("/categories/create")
    public String create(Model model){
        model.addAttribute("categoryForm", new Category());

        return "categories/create";
    }

    @GetMapping("/categories/update/{id}")
    public String update (@PathVariable("id") Long id, Model model) {
        Category getCategory = categoryService.findById(id);
        if (getCategory == null) {
            return "redirect:/categories";
        }
        model.addAttribute("categoryForm", getCategory);

        return "categories/update";
    }

    @PostMapping("/categories/create")
    public String createAction(@ModelAttribute("categoryForm") Category categoryForm) {
        categoryService.save(categoryForm);
        return "redirect:/categories";
    }

    @PostMapping("/categories/update/{id}")
    public String updateAction(@ModelAttribute("categoryForm") Category categoryForm) {
        categoryService.save(categoryForm);
        return "redirect:/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteAction(@PathVariable("id") Long id) {
        if (categoryService.isExistsById(id)) {
            Category getCategory = categoryService.findById(id);
            getCategory.setIsDeleted(true);;
            categoryService.save(getCategory);
            return "redirect:/categories?delete=success";
        }else {
            return "redirect:/categories?delete=error";
        }
    }
}
