package com.haimp02.pointofsales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.haimp02.pointofsales.entity.User;
public interface UserRepository extends JpaRepository<User, Long>{
    
}
