package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    // 1. Show the blank form
    @GetMapping("/")
    public String showForm(Model model) {
        // We send an empty Student object to bind with the form fields
        model.addAttribute("student", new Student());
        return "student-form"; // Looks for student-form.html in templates
    }

    // 2. Handle the "Insert" button click
    @PostMapping("/insert")
    public String insertStudent(@ModelAttribute("student") Student student) {
        service.saveToBothDatabases(student);
        return "success"; // Redirects to success.html
    }
}