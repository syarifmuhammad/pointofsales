package com.haimp02.pointofsales.services;

import com.haimp02.pointofsales.models.entities.Customer;
import com.haimp02.pointofsales.models.entities.Transaction;
import com.haimp02.pointofsales.models.entities.TransactionDetail;
import com.haimp02.pointofsales.models.repositories.CustomerRepository;
import com.haimp02.pointofsales.models.repositories.TransactionDetailRepository;
import com.haimp02.pointofsales.models.repositories.TransactionRepository;
import com.haimp02.pointofsales.services.interfaces.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Integer getMonthlyEarning() {
        Optional<Integer> d = transactionRepository.getMonthlyEarning();
        if (d.isPresent()) {
            return d.get();
        }
        return 0;
    };

    @Override
    public Integer getAnnualEarning() {
        Optional<Integer> d = transactionRepository.getAnnualEarning();
        if (d.isPresent()) {
            return d.get();
        }
        return 0;
    };

    @Override
    public Integer getAnnualSales() {
        Optional<Integer> d = transactionRepository.getAnnualSales();
        if (d.isPresent()) {
            return d.get();
        }
        return 0;
    };

    @Override
    public ArrayList<Integer> getPerMonthEarning() {
        ArrayList<Integer> d =  new ArrayList<Integer>();
        for (Integer i=0; i<12; i++) {
            d.add(transactionRepository.getPerMonthEarning(i+1));
        }
        return d;
    };

    @Override
    public void deleteById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            Transaction t = transaction.get();
            List<TransactionDetail> delete = transactionDetailRepository.findByTransactionId(t.getId());
            delete.forEach(p -> {
                transactionDetailRepository.deleteById(p.getId());
            });
            transactionRepository.delete(t);
            customerRepository.delete(t.getCustomer());
        }
    }

    @Override
    public Page<Transaction> findAll(String search, Integer page) {
        Pageable pagination = PageRequest.of(page, 10);
        return transactionRepository.findTop10ByCustomerNameContaining(search, pagination);
    }

    @Override
    public Transaction findById(Long id) {
        Optional<Transaction> getTransaction = transactionRepository.findById(id);
        if (getTransaction.isPresent()) {
            return getTransaction.get();
        }
        return null;
    }

    @Override
    public Boolean isExistsById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Transaction transaction) {
        Customer costomer = customerRepository.save(transaction.getCustomer());
        transaction.getCustomer().setId(costomer.getId());
        transactionRepository.save(transaction);
        transaction.getTransaction_details().forEach(p -> {
            p.setTransaction(transaction);
            transactionDetailRepository.save(p);
        });
    }

    @Override
    public void update(Transaction transaction) {
        List<TransactionDetail> delete = transactionDetailRepository.findByTransactionId(transaction.getId());
        delete.forEach(p -> {
            transactionDetailRepository.deleteById(p.getId());
        });
        transaction.getTransaction_details().forEach(p -> {
            p.setTransaction(transaction);
            transactionDetailRepository.save(p);
        });
        customerRepository.save(transaction.getCustomer());
    }
    
}
