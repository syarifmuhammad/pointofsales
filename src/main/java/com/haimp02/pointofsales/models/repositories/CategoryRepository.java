package com.haimp02.pointofsales.models.repositories;

import com.haimp02.pointofsales.models.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{
}
