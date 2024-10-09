package com.projectX.webService.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.projectX.webService.configuration.ProjectXProperties;

import jakarta.annotation.PostConstruct;

@Service


public class EmailService {

    JavaMailSenderImpl mailSender;
    
    @Autowired
    ProjectXProperties projectXProperties;
    
    @PostConstruct
    public void initialize() {
        this.mailSender = new JavaMailSenderImpl();
        System.out.println("-----------------------");
        System.out.println(projectXProperties.getEmail().password());
        System.out.println("--------------------------");
        mailSender.setHost(projectXProperties.getEmail().host());
        mailSender.setPort(projectXProperties.getEmail().port());
        mailSender.setUsername(projectXProperties.getEmail().username());
        mailSender.setPassword(projectXProperties.getEmail().password());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", true);
    }
    
    public void sendActivationEmail(String email, String activationToken) {
        var activationUrl = projectXProperties.getClient().host() + "/activation/"+ activationToken;
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(projectXProperties.getEmail().from());
        message.setTo(email);
        message.setSubject("Email Activation");
        message.setText(activationUrl);
        this.mailSender.send(message);
    }


}
