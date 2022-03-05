package com.haimp02.pointofsales.services.interfaces;

import com.haimp02.pointofsales.models.entities.User;

import org.springframework.data.domain.Page;

public interface UserService {
    Page<User> findAll(Integer page);
    User findById(Long id);
    void save(User user);
    User findByEmail(String email);
    void update(User user);
}
