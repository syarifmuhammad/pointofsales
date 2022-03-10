package com.haimp02.pointofsales.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.haimp02.pointofsales.models.entities.Product;
import com.haimp02.pointofsales.services.interfaces.ProductService;

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
public class ProductController {

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String index(Model model, @RequestParam(required = false) Integer page) {
        if (page == null) {
            page = 0;
        }
        Page<Product> products = productService.findAll(page);
        model.addAttribute("products", products);
        int totalPages = products.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "products/index";
    }

    @GetMapping("/products/create")
    public String create(Model model) {
        model.addAttribute("productForm", new Product());
        return "products/create";
    }

    @PostMapping("/products/create")
    public String createAction(@ModelAttribute("productForm") Product productForm) {
        productService.save(productForm);
        return "redirect:/products";
    }

    @GetMapping("/products/update/{id}")
    public String update (@PathVariable("id") Long id, Model model) {
        Product getProduct= productService.findById(id);
        if (getProduct== null) {
            return "redirect:/products";
        }
        model.addAttribute("productForm", getProduct);

        return "products/update";
    }

    @PostMapping("/products/update/{id}")
    public String updateAction(@ModelAttribute("productForm") Product productForm) {
        productService.save(productForm);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteAction(@PathVariable("id") Long id) {
        if (productService.isExistsById(id)) {
            productService.deleteById(id);
            return "redirect:/products?delete=success";
        }else {
            return "redirect:/products?delete=error";
        }
    }
}
