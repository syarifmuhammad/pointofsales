package com.haimp02.pointofsales.services.interfaces;

import java.util.List;

import com.haimp02.pointofsales.models.entities.Category;

public interface CategoryService {
    List<Category> findAll();
    void save(Category category);
}
