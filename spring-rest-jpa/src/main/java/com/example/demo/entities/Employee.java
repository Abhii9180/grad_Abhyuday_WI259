package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity                   // Logic: Tells JPA this class maps to a database table
@Table(name = "EMP")      // Logic: Maps the class to the "EMP" table in H2
public class Employee {

    @Id                   // Logic: Marks this field as the Primary Key
    private int id;
    private String name;
    private int age; 
    private int salary;
    @Column(name = "ROLE") // Logic: Maps 'designation' variable to 'ROLE' column in DB
    private String designation;

    // --- CONSTRUCTORS ---
    // IMPORTANT: JPA requires a default (no-argument) constructor to work!
    public Employee() {
    }

    public Employee(int id, String name, int age, int salary, String designation) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.designation = designation;
    }

    // --- GETTERS AND SETTERS ---
    // These are used by Jackson (REST) to create JSON and by Hibernate to fill data.
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    // Logic: Useful for debugging in the Eclipse console
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", designation=" + designation + "]";
    }
}