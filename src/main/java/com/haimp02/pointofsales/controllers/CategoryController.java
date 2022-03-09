package com.haimp02.pointofsales.controllers;

import java.util.List;

import com.haimp02.pointofsales.models.entities.Category;
import com.haimp02.pointofsales.models.entities.Product;
import com.haimp02.pointofsales.services.interfaces.CategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        // List<Product> categories = categoryService.findAll();
        // model.addAttribute("categories", categories);
        return "categories/index";
    }
}
