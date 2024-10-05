package com.projectX.webService.user;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity //When we use this annotation, it will assume that this class has a matching table in the database, or it will create one.
@Table(name = "users", uniqueConstraints = @UniqueConstraint (columnNames = {"email"}))

public class User {

    @Id
    @GeneratedValue //delegate the operation of generating the ID to the database.
    long id;
    
    String username;
    
    String email;

    String password;

    private boolean active = false;
    
    private String activationToken;
    
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    

}
