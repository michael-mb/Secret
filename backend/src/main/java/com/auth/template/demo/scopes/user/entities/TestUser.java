package com.auth.template.demo.scopes.user.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum TestUser {

    USER_ONE("john" , "doe" , "john@doe.com" ,
            "test" ,
            new HashSet<UserRole>(Arrays.asList(UserRole.USER , UserRole.ADMIN , UserRole.MODERATOR))),

    USER_TWO("test" , "test" , "test@test.com" ,
            "test" ,
            new HashSet<UserRole>(Arrays.asList(UserRole.USER , UserRole.MODERATOR)));


    public final String firstname;
    public final String lastname;
    public final String email;
    public final String password;
    public final Set<UserRole> grantedAuthorities;
    TestUser(String firstname , String lastname , String email ,String password ,
             Set<UserRole> grantedAuthorities){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;

    }
}