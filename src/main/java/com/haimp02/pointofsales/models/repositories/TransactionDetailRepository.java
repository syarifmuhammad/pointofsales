package com.haimp02.pointofsales.models.repositories;

import java.util.List;

import com.haimp02.pointofsales.models.entities.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {
    void removeByTransactionId(Long id);
    List<TransactionDetail> findByTransactionId(Long id);
    void deleteById(Long id);
}