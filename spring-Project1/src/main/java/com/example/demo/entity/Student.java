package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_records")
public class Student {

    @Id
    private Integer rollNo; // Changed to Integer
    private String name;
    private String standard;
    private String school;

    // Default Constructor
    public Student() {}

    // Getters and Setters
    public Integer getRollNo() { 
        return rollNo; 
    }
    
    // Changed parameter to Integer to match the field
    public void setRollNo(Integer rollNo) { 
        this.rollNo = rollNo; 
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStandard() { return standard; }
    public void setStandard(String standard) { this.standard = standard; }

    public String getSchool() { return school; }
    public void setSchool(String school) { this.school = school; }
}