package com.haimp02.pointofsales.services.interfaces;

import com.haimp02.pointofsales.models.entities.Transaction;

import org.springframework.data.domain.Page;

public interface TransactionService {
    Page<Transaction> findAll(String search, Integer page);
    Transaction findById(Long id);
    void save(Transaction transaction);
    void update(Transaction transaction);
    Boolean isExistsById(Long id);
    void deleteById(Long id);
}
