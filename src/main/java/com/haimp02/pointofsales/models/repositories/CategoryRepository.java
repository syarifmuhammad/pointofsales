package com.haimp02.pointofsales.models.repositories;

import java.util.List;

import com.haimp02.pointofsales.models.entities.Category;
import com.haimp02.pointofsales.models.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    // List<Category> findByName(String search);
    Page<Category> findByName(String search, Pageable page);
    // User findByEmail(String email);
    // Page<User> findByFullnameContaining(String search, Pageable page);
    // void deleteById(Long id);
}
