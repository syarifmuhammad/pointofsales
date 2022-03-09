package com.haimp02.pointofsales.services.interfaces;

import java.util.List;

import com.haimp02.pointofsales.models.entities.Category;
import com.haimp02.pointofsales.models.entities.Product;

import org.springframework.data.domain.Page;

public interface CategoryService {
    // List<Product> findAll();
    Page<Category> findAll(Integer page, String search);
    void save(Category category);
    // Category findById(Long id);
    // void update(Category category);
    // Boolean isExistsById(Long id);
    // void deleteById(Long id);
}
