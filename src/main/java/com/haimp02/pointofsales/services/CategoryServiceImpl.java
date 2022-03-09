package com.haimp02.pointofsales.services;

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
    public void save(Category category) {
        // TODO Auto-generated method stub
    }

    @Override
    public Page<Category> findAll(Integer page, String search) {
        Pageable pagination = PageRequest.of(page, 10);
        return categoryRepository.findByName(search, pagination);
    }
    
}
