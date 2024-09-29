package com.projectX.webService.user.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.projectX.webService.user.User;
import com.projectX.webService.user.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{

    @Autowired
    UserRepository userRepository;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User inDB = userRepository.findByEmail(value);
        
        if(inDB != null){
            return false; 
        }
        return true;
       
    }
    
}
