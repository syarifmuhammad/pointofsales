package com.haimp02.pointofsales.validators;

import com.haimp02.pointofsales.models.entities.User;
import com.haimp02.pointofsales.services.interfaces.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    Logger logger = LoggerFactory.getLogger(UserValidator.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullname", "NotEmpty");
        if (user.getFullname().length() > 100) {
            errors.rejectValue("fullname", "Size.userForm.fullname");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail().length() < 8 || user.getEmail().length() > 32) {
            errors.rejectValue("email", "Size.userForm.email");
        }
        User getUser = userService.findByEmail(user.getEmail());
        if (getUser != null) {
            if (user.getId() != getUser.getId()) {
                errors.rejectValue("email", "Duplicate.userForm.email");
            }
        }
        // logger.error(String.valueOf(user.getId()));
        // logger.error(String.valueOf(user.getPassword()));
        if (user.getId() == null || !user.getPassword().isBlank()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
            if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
                errors.rejectValue("password", "Size.userForm.password");
            }
        } 
    }
}
