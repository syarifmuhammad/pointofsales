package com.haimp02.pointofsales.models.repositories;

import com.haimp02.pointofsales.models.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long>{
    // User findByUsername(String username);
    User findByEmail(String email);
}
