package com.auth.template.demo.scopes.auth.forms;

import com.auth.template.demo.validation.Regexes;
import com.auth.template.demo.validation.ValidEmail;

import javax.validation.constraints.*;

public class SignUpDto {
    @NotBlank
    @Size(max=200)
    @ValidEmail
    public String email;


    @NotBlank
    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    public String firstname;

    @NotBlank
    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.TEXT, message = "{error.invalid.field.text}")
    public String lastname;

    @NotBlank
    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.PASSWORD, message = "{error.registration.invalid.password}")
    public String password;

    @NotBlank
    @Size(max = 200, message = "{error.invalid.length}")
    @Pattern(regexp = Regexes.PASSWORD, message = "{error.registration.invalid.password}")
    public String passwordAgain;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }


    @Override
    public String toString(){
        return "UserRegistrationDto{"+
                "email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", Password='" + password + '\'' +
                ", PasswordAgain='" + passwordAgain + '\'' +
                '}' ;
    }

}
