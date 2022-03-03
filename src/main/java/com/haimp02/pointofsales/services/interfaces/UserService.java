package com.haimp02.pointofsales.services.interfaces;

import java.util.List;

import com.haimp02.pointofsales.models.entities.User;

public interface UserService {
    List<User> findAll();
    void save(User user);
    User findByEmail(String email);
}
