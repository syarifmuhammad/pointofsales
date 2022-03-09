package com.haimp02.pointofsales.services;

import java.util.Optional;

import com.haimp02.pointofsales.models.entities.Category;
import com.haimp02.pointofsales.models.repositories.CategoryRepository;
import com.haimp02.pointofsales.services.interfaces.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        Optional<Category> getCategory = categoryRepository.findById(id);
        if (getCategory.isPresent()) {
            return getCategory.get();
        }
        return null;
    }

    @Override
    public void save(Category category) { 
        categoryRepository.save(category);
    }

    @Override
    public Page<Category> findAll(Integer page) {
        Pageable pagination = PageRequest.of(page, 10);
        return categoryRepository.findAll(pagination);
    }
    
    @Override
    public Boolean isExistsById(Long id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
