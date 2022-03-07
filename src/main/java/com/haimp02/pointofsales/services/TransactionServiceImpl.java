package com.haimp02.pointofsales.services;

import com.haimp02.pointofsales.models.entities.Transaction;
import com.haimp02.pointofsales.models.repositories.TransactionRepository;
import com.haimp02.pointofsales.services.interfaces.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Page<Transaction> findAll(String search, Integer page) {
        Pageable pagination = PageRequest.of(page, 10);
        return transactionRepository.findTop10ByCustomerNameContaining(search, pagination);
    }

    @Override
    public Transaction findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean isExistsById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Transaction transaction) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Transaction transaction) {
        // TODO Auto-generated method stub
        
    }
    
}
