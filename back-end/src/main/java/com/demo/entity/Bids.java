package com.demo;

import org.springframework.data.annotation.Id;

public class Bids{
    @Id
    public String id;

    public String projecttitle;
    public String projectid;
    public String price;
    public String period;
    public String bidby;

    public Bids(String projecttitle, String projectid, String price, String period, String bidby){
        this.projecttitle = projecttitle;
        this.projectid = projectid;
        this.price = price;
        this.period = period;
        this.bidby = bidby;
    }
}