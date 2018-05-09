package com.demo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.demo.entity.*;

public interface BidsSpringRepository extends MongoRepository<Bids, String>{
    public Bids findByBidbyAndProjectid(String bidby, String projectid);
    public List<Bids> findByBidby(String bidby);
    public List<Bids> findByProjectid(String projectid);
}