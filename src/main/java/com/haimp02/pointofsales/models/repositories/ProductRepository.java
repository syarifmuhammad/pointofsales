package com.haimp02.pointofsales.models.repositories;

import java.util.List;

import com.haimp02.pointofsales.models.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String search);
}
