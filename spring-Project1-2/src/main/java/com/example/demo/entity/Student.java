package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private Long univRegNo; // Primary Key
    private String name;
    private String standard;
    private String school;
    private String gender;
    private Double percentage;

    // 1. Default Constructor (Required by JPA)
    public Student() {}

    // 2. Parameterized Constructor
    public Student(Long univRegNo, String name, String standard, String school, String gender, Double percentage) {
        this.univRegNo = univRegNo;
        this.name = name;
        this.standard = standard;
        this.school = school;
        this.gender = gender;
        this.percentage = percentage;
    }

    // 3. Getters and Setters
    public Long getUnivRegNo() { 
        return univRegNo; 
    }

    public void setUnivRegNo(Long univRegNo) { 
        this.univRegNo = univRegNo; 
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public String getStandard() { 
        return standard; 
    }

    public void setStandard(String standard) { 
        this.standard = standard; 
    }

    public String getSchool() { 
        return school; 
    }

    public void setSchool(String school) { 
        this.school = school; 
    }

    public String getGender() { 
        return gender; 
    }

    public void setGender(String gender) { 
        this.gender = gender; 
    }

    public Double getPercentage() { 
        return percentage; 
    }

    public void setPercentage(Double percentage) { 
        this.percentage = percentage; 
    }
}