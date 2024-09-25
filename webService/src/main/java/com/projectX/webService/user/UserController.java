package com.projectX.webService.user;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {
    @PostMapping("/api/v1/users")
    void createUser(){}
}
