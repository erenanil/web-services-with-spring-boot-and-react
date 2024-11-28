package com.projectX.webService.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectX.webService.email.EmailService;
import com.projectX.webService.user.exception.ActivationNotificationException;
import com.projectX.webService.user.exception.InvalidTokenException;
import com.projectX.webService.user.exception.NotUniqueEmailException;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Transactional(rollbackOn = MailException.class)
    public void save(User user) {
        /*
         * String encodedPassowrd = passwordEncoder.encode(user.getPassword());
         * user.setPassword(encodedPassowrd);
         */

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword())); // inline usage.
            user.setActivationToken(java.util.UUID.randomUUID().toString());
            userRepository.saveAndFlush(user);
            emailService.sendActivationEmail(user.email , user.getActivationToken());
        } catch (DataIntegrityViolationException ex) {
            throw new NotUniqueEmailException();
        }catch (MailException ex){
            throw new ActivationNotificationException();
        }
    }

    public void activateUser(String token) {
        User inDB = userRepository.findByActivationToken(token);
        if(inDB == null){
            throw new InvalidTokenException();
        }
        inDB.setActive(true);
        inDB.setActivationToken(null);
        userRepository.save(inDB);
    }

    public Page<User> getUsers(Pageable page) {
        return userRepository.findAll(page);
    }

    
}
