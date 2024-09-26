package com.projectX.webService.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectX.webService.error.ApiError;
import com.projectX.webService.shared.GenericMessage;

@RestController
public class UserController {

    

    @Autowired
    UserService userService;

    @PostMapping("/api/v1/users")
    ResponseEntity<?> createUser(@RequestBody User user){
        if (user.getUsername() == null || user.getUsername().isEmpty()){
            ApiError apiError = new ApiError();
            apiError.setMessage("validation error");
            apiError.setPath("/api/v1/users");
            apiError.setStatus(400);
            Map<String, String> validationErrors= new HashMap<>();
            validationErrors.put("username", "Username cannot be null");
            apiError.setValidationErrors(validationErrors); 
            return ResponseEntity.badRequest().body(apiError);
        }


        userService.save(user);
        return ResponseEntity.ok(new GenericMessage("User is created."));
    }



}
