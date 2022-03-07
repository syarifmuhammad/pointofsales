package com.haimp02.pointofsales.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.haimp02.pointofsales.models.entities.Product;
import com.haimp02.pointofsales.models.entities.Transaction;
import com.haimp02.pointofsales.models.entities.TransactionDetail;
import com.haimp02.pointofsales.services.interfaces.ProductService;
import com.haimp02.pointofsales.services.interfaces.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransactionController {

    @Autowired
    private ProductService productionService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public String index(Model model, @RequestParam(required = false) Integer page,
            @RequestParam(required = false) String search) {
        if (page == null || page < 1) {
            page = 1;
        }
        page--;
        if (search == null) {
            search = "";
        } else {
            model.addAttribute("search", search);
        }
        Page<Transaction> transactions = transactionService.findAll(search, page);
        model.addAttribute("transactions", transactions);
        model.addAttribute("page", page);
        model.addAttribute("element", transactions.getContent().size());

        return "transactions/index";
    }

    @RequestMapping({ "/transactions/create" })
    public String showTransactionCreate(Model model) {
        Transaction newTransaction = new Transaction();
        model.addAttribute("transaction", newTransaction);
        model.addAttribute("products", new ArrayList<Product>());
        return "transactions/create";
    }

    @RequestMapping(value = "/transactions/create", params = { "cari" })
    public String searchProduct(final Transaction transaction, Model model, final BindingResult bindingResult,
            final HttpServletRequest req) {
        if (req.getParameter("search") == null || req.getParameter("search").isBlank()) {
            model.addAttribute("products", new ArrayList<Product>());
        } else {
            List<Product> products = productionService.findByNameContaining(req.getParameter("search"));
            model.addAttribute("products", products);
        }
        return "transactions/create";
    }

    @RequestMapping(value = "/transactions/create", params = { "addTransactionDetails" })
    public String addTransactionDetails(final Transaction transaction, Model model, final BindingResult bindingResult,
            final HttpServletRequest req) {
        TransactionDetail newTransactionDetail = new TransactionDetail();
        newTransactionDetail.setProduct(productionService.findById(Long.parseLong(req.getParameter("addTransactionDetails"))));
        newTransactionDetail.setQuantity(1);
        if (transaction.getTransaction_details() == null) {
            transaction.setTransaction_details(new ArrayList<TransactionDetail>());
        }
        transaction.getTransaction_details().add(newTransactionDetail);
        model.addAttribute("transaction", transaction);
        return "transactions/create";
    }
}
