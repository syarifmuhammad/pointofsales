package com.haimp02.pointofsales.services.interfaces;

import com.haimp02.pointofsales.models.entities.Category;

import org.springframework.data.domain.Page;

public interface CategoryService {
    Page<Category> findAll(Integer page);
    void save(Category category);
    Category findById(Long id);
}
