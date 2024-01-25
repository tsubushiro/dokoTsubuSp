package com.example.demo.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.RegisterUser;
import com.example.demo.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UnusedNameValidator implements ConstraintValidator<UnusedName, String> {
    @Autowired
    UserService userService;

    @Override
    public void initialize(UnusedName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        boolean result = userService.registerCheck(new RegisterUser(userName,"",0));
        // 既存ユーザに登録済だとFalseを返す
        if( result == true) {
            return false;
        }
        
        return true;
    }

}
