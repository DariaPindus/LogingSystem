package com.daria.sprimg.mvc.validator;


import com.daria.sprimg.mvc.model.User;
import org.springframework.validation.Errors;

import javax.validation.Validator;

public class UserValidator implements org.springframework.validation.Validator{


    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {

    }
}
