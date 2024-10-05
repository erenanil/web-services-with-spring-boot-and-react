package com.projectX.webService.user;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectX.webService.user.exception.ActivationNotificationException;
import com.projectX.webService.user.exception.NotUniqueEmailException;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;

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
            sendActivationEmail(user);
        } catch (DataIntegrityViolationException ex) {
            throw new NotUniqueEmailException();
        }catch (MailException ex){
            throw new ActivationNotificationException();
        }
    }

    private void sendActivationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@my-app.com");
        message.setTo(user.getEmail());
        message.setSubject("Email Activation");
        message.setText("http://localhost:5173/activation/" + user.getActivationToken());
        getJavaMailSender().send(message);
    }

    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.ethereal.email");
        mailSender.setPort(587);
        mailSender.setUsername("cordie.breitenberg@ethereal.email");
        mailSender.setPassword("ahP45dPe4KDC6kHC2q-");

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", true);
        return mailSender;
    }
}
