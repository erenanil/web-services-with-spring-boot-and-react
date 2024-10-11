package com.projectX.webService.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.projectX.webService.configuration.ProjectXProperties;
import com.projectX.webService.shared.Messages;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    
    JavaMailSenderImpl mailSender;

    @Autowired
    ProjectXProperties projectXProperties;
    
    @PostConstruct
    public void initialize() {
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost(projectXProperties.getEmail().host());
        mailSender.setPort(projectXProperties.getEmail().port());
        mailSender.setUsername(projectXProperties.getEmail().username());
        mailSender.setPassword(projectXProperties.getEmail().password());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", true);
    }

    String activationEmail = """
            <html>
                <body>
                    <h1>${title}</h1>
                    <a href="${url}">${clickHere}</a>
                </body>
            </html>
            """;

    public void sendActivationEmail(String email, String activationToken) {
        
        var activationUrl = projectXProperties.getClient().host() + "/activation/" + activationToken;
        var title=Messages.getMessageForLocale("projectx.mail.user.created.title", LocaleContextHolder.getLocale());
        var clickHere=Messages.getMessageForLocale("projectx.mail.click.here", LocaleContextHolder.getLocale());
        
        var mailBody = activationEmail
                .replace("${url}", activationUrl)
                .replace("${title}", title)
                .replace("${clickHere}", clickHere);
           

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage,"UTF-8");

        try {
            message.setFrom(projectXProperties.getEmail().from());
            message.setTo(email);
            message.setSubject(title);
            message.setText(mailBody, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        this.mailSender.send(mimeMessage);
    }

}
