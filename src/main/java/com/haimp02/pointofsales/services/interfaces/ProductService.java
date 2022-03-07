package com.haimp02.pointofsales.services.interfaces;

import java.util.List;

import com.haimp02.pointofsales.models.entities.Product;

import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> findAll(int page);
    Product findById(Long id);
    List<Product> findByNameContaining(String search);
    void save(Product product);
}
