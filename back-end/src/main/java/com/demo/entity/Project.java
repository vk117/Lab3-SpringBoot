package com.demo;

import org.springframework.data.annotation.Id;

public class Project{
    @Id
    public String id;

    public String title;
    public String description;
    public String skills;
    public String budget;
    public String user;
    //public boolean status;
    //public String assigned_to;

    public Project(String title, String description, String skills, String budget, String  user){
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.budget = budget;
        this.user = user;
        //this.status = status;
        //this.assigned_to = assigned_to;
    }

    //public Project(){}

}