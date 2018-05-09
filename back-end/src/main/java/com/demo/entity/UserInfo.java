package com.demo;

import org.springframework.data.annotation.Id;

public class UserInfo {
    @Id
    public String id;

    public String name;
    public String email;
    public String phone;
    public String about;
    public String skills;

    public UserInfo(String name, String email, String phone, String about, String skills){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.about = about;
        this.skills = skills;
    }

}