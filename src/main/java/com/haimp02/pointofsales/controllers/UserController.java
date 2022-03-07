package com.haimp02.pointofsales.controllers;

import com.haimp02.pointofsales.models.entities.User;
import com.haimp02.pointofsales.services.interfaces.SecurityService;
import com.haimp02.pointofsales.services.interfaces.UserService;
import com.haimp02.pointofsales.validators.UserValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null) {
            model.addAttribute("error", "Your email and password is invalid.");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("userForm", new User());

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.save(userForm);
        model.addAttribute("success", true);
        return "register";
    }


    // Route untuk direct ke halaman

    @GetMapping("/users")
    public String index(Model model, @RequestParam(required=false) Integer page, @RequestParam(required=false) String search) {
        if (page == null || page<1) {
            page = 1;
        }
        page--;
        if (search == null) {
            search = "";
        } else {
            model.addAttribute("search", search);
        }
        Page<User> users = userService.findAll(page, search);
        model.addAttribute("users", users);
        model.addAttribute("page", page);
        model.addAttribute("element", users.getContent().size());

        return "users/index";
    }

    @GetMapping("/users/create")
    public String create (Model model) {
        model.addAttribute("userForm", new User());

        return "users/create";
    }

    @GetMapping("/users/update/{id}")
    public String update (@PathVariable("id") Long id, Model model) {
        User getUser = userService.findById(id);
        if (getUser == null) {
            return "redirect:/users";
        }
        model.addAttribute("userForm", getUser);

        return "users/update";
    }


    //route method post 

    @PostMapping("/users/create")
    public String createAction(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "users/create";
        }
        userService.save(userForm);
        return "redirect:/users";
    }

    @PostMapping("/users/update/{id}")
    public String updateAction(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "users/update";
        }
        userService.save(userForm);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteAction(@PathVariable("id") Long id) {
        if (userService.isExistsById(id)) {
            userService.deleteById(id);
            return "redirect:/users?delete=success";
        }else {
            return "redirect:/users?delete=error";
        }
    }
}
