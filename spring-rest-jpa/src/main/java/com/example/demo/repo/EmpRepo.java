package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Employee;

/**
 * JpaRepository takes two parameters:
 * 1. The Entity class it manages (Employee)
 * 2. The data type of the Primary Key (@Id), which is Integer in our case.
 */
public interface EmpRepo extends JpaRepository<Employee, Integer> {

  
}