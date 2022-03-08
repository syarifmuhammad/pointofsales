package com.haimp02.pointofsales.models.repositories;

import java.util.List;

import com.haimp02.pointofsales.models.entities.Product;

// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
// import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String search);
    // Page<Product> findByFullnameContaining(String search, Pageable page);
    // void deleteById(Long id);
}
