package com.demo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.demo.entity.*;

public interface ProjectSpringRepository extends MongoRepository<Project, String>{
    public List<Project> findByUser(String user);
    public Long countByUser(String user);
    public Project findByTitle(String title);
}