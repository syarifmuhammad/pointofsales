package com.haimp02.pointofsales.models.repositories;

import com.haimp02.pointofsales.models.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
