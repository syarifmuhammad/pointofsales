package com.haimp02.pointofsales.controllers;

import com.haimp02.pointofsales.models.entities.User;
import com.haimp02.pointofsales.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestUserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/users/name")
    public String getFullName(Authentication auth) {
        User user = userService.findByEmail(auth.getName());
        return user.getFullname();
    }
}
