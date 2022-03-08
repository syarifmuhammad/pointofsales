package com.haimp02.pointofsales.controllers;

import com.haimp02.pointofsales.services.interfaces.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {
    
    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/categories")
    public String index(Model model){
        // , @RequestParam(required = false) Integer page
        return "categories/index";
    }
}
