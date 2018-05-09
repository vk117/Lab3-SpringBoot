package com.demo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.demo.entity.*;

public interface UserSpringRepository extends MongoRepository<User, String>{
    public User findByEmail(String email);
}
