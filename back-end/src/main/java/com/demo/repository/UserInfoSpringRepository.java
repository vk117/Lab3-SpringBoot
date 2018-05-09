package com.demo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.demo.entity.*;

public interface UserInfoSpringRepository extends MongoRepository<UserInfo, String>{
    public UserInfo findByEmail(String email);
}