package com.demo;

import org.springframework.data.annotation.Id;

public class User{
    @Id
    public String id;

    public String email;
    public String password;
    public String uname;

    public User(String email, String password, String uname){
        this.email = email;
        this.password = password;
        this.uname = uname;
    }
}


