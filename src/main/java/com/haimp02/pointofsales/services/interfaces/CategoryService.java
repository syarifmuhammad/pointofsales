package com.haimp02.pointofsales.services.interfaces;

import java.util.List;

import com.haimp02.pointofsales.models.entities.Category;

import org.springframework.data.domain.Page;

public interface CategoryService {
    List<Category> findAll();
    Page<Category> findAll(Integer page);
    void save(Category category);
    Category findById(Long id);
    Boolean isExistsById(Long id);
    void deleteById(Long id);
}
