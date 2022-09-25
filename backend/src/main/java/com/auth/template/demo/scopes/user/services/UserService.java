package com.auth.template.demo.scopes.user.services;


import com.auth.template.demo.scopes.auth.forms.SignUpDto;
import com.auth.template.demo.scopes.token.TokenServiceImpl;
import com.auth.template.demo.scopes.user.entities.User;
import com.auth.template.demo.scopes.user.entities.UserRole;
import com.auth.template.demo.scopes.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public TokenServiceImpl tokenService;

    @Autowired
    public  PasswordEncoder passwordEncoder;


    public void saveUser(final User user){
        if(user == null) throw new NullPointerException("User must not be null");
        userRepository.save(user);
    }

    public void generateAndSaveNewValidationTokenForUser(final User user) {
        if (user == null) throw new NullPointerException("User must not be null.");
        String token = tokenService.createToken(user);
        user.setValidationToken(token);
        saveUser(user);
    }


    public void updatePassword(final String newPassword, final User user) {
        if (newPassword == null) throw new NullPointerException("NewPassword must not be null.");
        if (newPassword.isEmpty()) throw new IllegalArgumentException("NewPassword must not be empty.");
        if (user == null) throw new NullPointerException("User must not be null.");

        user.hashedPassword = passwordEncoder.encode(newPassword);
        saveUser(user);
    }

    public void addUser(SignUpDto signUpDto){
        if (signUpDto == null) throw new NullPointerException("signUpDto must not be null.");

        User user = new User(signUpDto.firstname, signUpDto.lastname, signUpDto.email,
                signUpDto.password, new HashSet<UserRole>(Arrays.asList(UserRole.USER)));

        generateAndSaveNewValidationTokenForUser(user);
        rehashPassword(user.hashedPassword , user);
    }
    public void rehashPassword(final String password, final User user) {
        this.updatePassword(password, user);
    }

    public boolean isPasswordCorrect(final String password, final User user) {
        if (password == null) throw new NullPointerException("Password must not be null.");
        if (password.isEmpty()) throw new IllegalArgumentException("Password must not be empty.");
        if (user == null) throw new NullPointerException("User must not be null.");

        return passwordEncoder.matches(password, user.getHashedPassword());
    }

    public Optional<User> findUserByEmail(String email) {
        if (email == null) throw new NullPointerException("EMail must not be null.");
        if (email.isEmpty()) throw new NullPointerException("EMail must not be empty.");

        return userRepository.findFirstByEmail(email.toLowerCase().trim());
    }

    public Optional<User> findUserByToken(String token) {
        if (token == null) throw new NullPointerException("Token must not be null.");
        return userRepository.findFirstByEmail(tokenService.getEmailFromToken(token).toLowerCase().trim());
    }

    public Optional<User> findUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        return byId;
    }

    public boolean doesEmailAlreadyExists(final String email) {
        if (email == null) throw new NullPointerException("Email must not be null.");
        if (email.isEmpty()) throw new NullPointerException("Email must not be empty.");

        return findUserByEmail(email).isPresent();
    }

    public String getEmailFromId(Long id){
        Optional<User> user = findUserById(id);
        if(user.isEmpty())
            return null;
        return user.get().getEmail();
    }

    public Long getIdFromEmail(String email){
        Optional<User> user = findUserByEmail(email);
        if(user.isEmpty())
            return null;

        return user.get().getId();
    }

    private String capitalize(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
