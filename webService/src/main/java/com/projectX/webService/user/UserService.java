package com.projectX.webService.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

@Autowired
UserRepository userRepository;

public void save(User user){
    /* 
     String encodedPassowrd = passwordEncoder.encode(user.getPassword());
     user.setPassword(encodedPassowrd);
     */
    user.setPassword(passwordEncoder.encode(user.getPassword())); // inline usage.
    userRepository.save(user);
 }

}
