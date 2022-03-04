package com.haimp02.pointofsales.services.interfaces;

import com.haimp02.pointofsales.models.entities.Product;

import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> findAll(int page);
    Product findById(Long id);
    void save(Product product);
}
