package com.projectX.webService.email;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service


public class EmailService {

    public EmailService(){
        this.initialize();
    }

    JavaMailSenderImpl mailSender;
    
    public void initialize() {
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.ethereal.email");
        mailSender.setPort(587);
        mailSender.setUsername("cordie.breitenberg@ethereal.email");
        mailSender.setPassword("ahP45dPe4KDC6kHC2q");

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", true);
    }
    
    public void sendActivationEmail(String email, String activationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@my-app.com");
        message.setTo(email);
        message.setSubject("Email Activation");
        message.setText("http://localhost:5173/activation/" + activationToken);
        this.mailSender.send(message);
    }


}
