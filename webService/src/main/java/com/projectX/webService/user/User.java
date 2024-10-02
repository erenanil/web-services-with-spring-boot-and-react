package com.projectX.webService.user;

import com.projectX.webService.user.validation.UniqueEmail;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity //When we use this annotation, it will assume that this class has a matching table in the database, or it will create one.
@Table(name = "users", uniqueConstraints = @UniqueConstraint (columnNames = {"email"}))

public class User {

    @Id
    @GeneratedValue //delegate the operation of generating the ID to the database.
    long id;
    
    @NotBlank(message = "{projectX.constraint.username.notBlank}" )
    @Size(min=4, max=255)
    String username;
    
    
    @NotBlank
    @Email
    @UniqueEmail
    String email;


    @Size(min=8, max=255)
    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{projectX.constraint.password.pattern}")
    String password;

     
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
    

}
