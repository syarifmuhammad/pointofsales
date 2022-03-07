package com.haimp02.pointofsales.models.repositories;

import com.haimp02.pointofsales.models.entities.Transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableJpaRepositories
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    
    // @Query("SELECT T.id FROM Transaction T")
    Page<Transaction> findTop10ByCustomerNameContaining(String search, Pageable page);

}
