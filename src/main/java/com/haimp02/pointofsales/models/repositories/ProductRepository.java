package com.haimp02.pointofsales.models.repositories;

import java.util.List;

import com.haimp02.pointofsales.models.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
// import org.springframework.data.domain.Page;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Page<Product> findByNameContainingAndIsDeletedFalse(String search, Pageable page);
    // Page<Product> findByFullnameContaining(String search, Pageable page);
    // void deleteById(Long id);

    @Query(value = "SELECT name, sum(td.quantity) sales FROM transaction_details td, products p WHERE p.id = td.product_id GROUP BY p.id ORDER BY sales DESC LIMIT 10", nativeQuery = true)
    List<?> getTopTenProducts();
}
