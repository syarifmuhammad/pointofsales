package com.haimp02.pointofsales.models.repositories;

import java.util.List;
import java.util.Optional;

import com.haimp02.pointofsales.models.entities.Transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableJpaRepositories
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    // @Query("SELECT T.id FROM Transaction T")
    Page<Transaction> findTop10ByCustomerNameContaining(String search, Pageable page);

    @Query(value = "SELECT SUM(tr.quantity * (pr.selling_price - pr.purchase_price)) FROM transactions trs, transaction_details tr, products pr WHERE trs.id = tr.transaction_id AND tr.product_id = pr.id AND MONTH(transaction_date) = MONTH(CURRENT_DATE()) AND YEAR(transaction_date) = YEAR(CURRENT_DATE())", nativeQuery = true)
    Optional<Integer> getMonthlyEarning();

    @Query(value = "SELECT SUM(tr.quantity * (pr.selling_price - pr.purchase_price)) FROM transactions trs, transaction_details tr, products pr WHERE trs.id = tr.transaction_id AND tr.product_id = pr.id AND YEAR(transaction_date) = YEAR(CURRENT_DATE())", nativeQuery = true)
    Optional<Integer> getAnnualEarning();

    @Query(value = "SELECT SUM(quantity) FROM transactions trs, transaction_details tr WHERE trs.id = tr.transaction_id AND YEAR(transaction_date) = YEAR(CURRENT_DATE())", nativeQuery = true)
    Optional<Integer> getAnnualSales();

    @Query(value = "SELECT COALESCE((SELECT SUM(tr.quantity * (pr.selling_price - pr.purchase_price)) earn FROM transactions trs, transaction_details tr, products pr WHERE trs.id = tr.transaction_id AND tr.product_id = pr.id AND YEAR(transaction_date) = YEAR(CURRENT_DATE()) AND MONTH(transaction_date) = ?1), 0)", nativeQuery = true)
    Integer getPerMonthEarning(Integer month);

}
