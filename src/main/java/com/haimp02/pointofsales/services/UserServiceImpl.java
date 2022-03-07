package com.haimp02.pointofsales.services;

import java.util.Optional;

import com.haimp02.pointofsales.models.entities.User;
import com.haimp02.pointofsales.models.repositories.UserRepository;
import com.haimp02.pointofsales.services.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Page<User> findAll(Integer page, String search) {  
        Pageable pagination = PageRequest.of(page, 10);
        return userRepository.findByFullnameContaining(search, pagination);
    }

    @Override
    public User findById(Long id) {
        Optional<User> getUser = userRepository.findById(id);
        if (getUser.isPresent()) {
            return getUser.get();
        }
        return null;
    }

    @Override
    public void save(User user) {
        if (user.getPassword().isBlank()) {
            User getUser = this.findById(user.getId());
            user.setPassword(getUser.getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void update(User user) {
        Optional<User> updateUser = userRepository.findById(user.getId());
        if (!updateUser.isEmpty()) {
            userRepository.save(updateUser.get());
        }
    }

    @Override
    public Boolean isExistsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    

    
}
