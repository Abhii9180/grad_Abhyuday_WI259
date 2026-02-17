package com.example.demo.repoPG;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Student;

@Repository
public interface StudentRepoPG extends JpaRepository<Student, Integer> {
    // Basic CRUD operations are inherited
}