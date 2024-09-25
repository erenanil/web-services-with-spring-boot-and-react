package com.projectX.webService.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectX.webService.shared.GenericMessage;


@Service
public class UserService {

 @Autowired
 UserRepository userRepository;

 GenericMessage void save(User user){
    userRepository.save(user);
    return new GenericMessage("user is created");
 }

}
