package com.example.demo.repoH2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Student;

@Repository
public interface StudentRepoH2 extends JpaRepository<Student, Integer> {
    // Basic CRUD operations are inherited
}