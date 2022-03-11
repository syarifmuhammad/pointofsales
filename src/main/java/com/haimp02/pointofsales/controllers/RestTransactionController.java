package com.haimp02.pointofsales.controllers;

import java.util.ArrayList;
import java.util.List;
import com.haimp02.pointofsales.models.entities.Product;
import com.haimp02.pointofsales.services.interfaces.ProductService;
// import com.haimp02.pointofsales.services.interfaces.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTransactionController {
    // @Autowired
    // private TransactionService transactionService;
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET, value = "/products/search")
    public List<Product> searchProduct(@RequestParam String search) {
        if (search.isBlank()) {
            return new ArrayList<Product>();
        }
        return productService.findByNameContaining(search);
        // return theaterService.getMovies();
    }
}
