package com.haimp02.pointofsales.controllers;

import com.haimp02.pointofsales.models.entities.Transaction;
import com.haimp02.pointofsales.services.interfaces.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class TransactionController {

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

    @GetMapping({ "/transactions/create" })
    public String showTransactionCreate(Model model) {
        Transaction newTransaction = new Transaction();
        model.addAttribute("transaction", newTransaction);
        return "transactions/create";
    }

    @PostMapping({ "/transactions/create" })
    public String createAction(Transaction transaction) {
        if (transaction.getTransaction_details() == null) {
            return "redirect:/transactions/create" + "?error=transaction_details_missing";
        }
        Transaction newTransaction = transaction;
        transaction.setTransaction_date(new Date());
        transactionService.save(newTransaction);

        return "redirect:/transactions";
    }

    @GetMapping({ "/transactions/update/{id}" })
    public String update(@PathVariable("id") Long id, Model model) {
        Transaction getTransaction = transactionService.findById(id);
        if (getTransaction == null) {
            return "redirect:/transactions";
        }
        model.addAttribute("transaction", getTransaction);
        model.addAttribute("transaction_details", getTransaction.getTransaction_details());

        return "transactions/update";
    }

    @PostMapping({ "/transactions/update/{id}" })
    public String updateAction(Transaction transaction) {
        Transaction editTransaction = transactionService.findById(transaction.getId());
        if (editTransaction == null || transaction.getTransaction_details() == null) {
            return "redirect:/transactions/update/" + transaction.getId() + "?error=transaction_details_missing";
        }
        transactionService.update(transaction);

        return "redirect:/transactions";
    }

    @GetMapping({ "/transactions/delete/{id}" })
    public String deleteAction(@PathVariable("id") Long id) {
        Transaction delete = transactionService.findById(id);
        if (delete != null) {
            transactionService.deleteById(id);;
        }

        return "redirect:/transactions";
    }
}
