package com.haimp02.pointofsales.models.repositories;

import com.haimp02.pointofsales.models.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
// import org.springframework.data.domain.Page;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Page<Product> findByNameContainingAndIsDeletedFalse(String search, Pageable page);
    // Page<Product> findByFullnameContaining(String search, Pageable page);
    // void deleteById(Long id);
}
