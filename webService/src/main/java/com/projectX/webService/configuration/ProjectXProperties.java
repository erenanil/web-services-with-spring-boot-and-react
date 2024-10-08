package com.projectX.webService.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "projectx")
@Configuration
public class ProjectXProperties {

    private Email email;

    private Client client;

    public static record  Email(
        String username,
        String password,
        Integer port,
        String host,
        String from

    ) {}
    public static record Client(
        String host
    ) {}

    public Email getEmail() {
        return email;
    }
    public void setEmail(Email email) {
        this.email = email;
    }

    
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}
