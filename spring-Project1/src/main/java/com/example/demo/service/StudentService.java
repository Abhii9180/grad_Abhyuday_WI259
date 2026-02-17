package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Student;
import com.example.demo.repoH2.StudentRepoH2;
import com.example.demo.repoPG.StudentRepoPG;

@Service
public class StudentService {

    @Autowired
    private StudentRepoH2 h2Repo;

    @Autowired
    private StudentRepoPG pgRepo;

    public void saveToBothDatabases(Student student) {
        // Step 1: Save to H2 (Primary)
        h2Repo.save(student);
        
        // Step 2: Save to Postgres (Secondary)
        pgRepo.save(student);
        
        System.out.println("Data saved successfully to both H2 and Postgres!");
    }
}