package com.haimp02.pointofsales.models.repositories;

import com.haimp02.pointofsales.models.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository<User, Long>{
    // User findByUsername(String username);
    User findByEmail(String email);
    Page<User> findByFullnameContaining(String search, Pageable page);
    void deleteById(Long id);
}
