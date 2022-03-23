package com.haimp02.pointofsales.controllers;

import java.text.DecimalFormat;

import com.haimp02.pointofsales.services.interfaces.ProductService;
import com.haimp02.pointofsales.services.interfaces.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
    
    @Autowired
    TransactionService transactionService;

    @Autowired
    ProductService productService;

    @RequestMapping("/")
    String index(Model model) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        model.addAttribute("monthly", df.format(transactionService.getMonthlyEarning()));
        model.addAttribute("annualy", df.format(transactionService.getAnnualEarning()));
        model.addAttribute("annualy_sales", df.format(transactionService.getAnnualSales()));
        model.addAttribute("earning_per_month", transactionService.getPerMonthEarning());
        model.addAttribute("top_ten", productService.getTopTenProducts());
        return "index";
    }

}
