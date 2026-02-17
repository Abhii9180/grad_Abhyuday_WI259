package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public List<Student> getAll() { return service.getAll(); }

    @GetMapping("/{regNo}")
    public Student getOne(@PathVariable Long regNo) { return service.getByRegNo(regNo).orElse(null); }

    @PostMapping
    public Student create(@RequestBody Student s) { return service.save(s); }

    @PutMapping("/{regNo}")
    public Student update(@PathVariable Long regNo, @RequestBody Student s) {
        s.setUnivRegNo(regNo);
        return service.save(s);
    }

    @PatchMapping("/{regNo}")
    public Student patch(@PathVariable Long regNo, @RequestBody Student s) {
        return service.patchStudent(regNo, s);
    }

    @DeleteMapping("/{regNo}")
    public String delete(@PathVariable Long regNo) {
        service.delete(regNo);
        return "Student " + regNo + " removed successfully.";
    }

    // Filter by School
    @GetMapping("/school")
    public List<Student> getBySchool(@RequestParam String name) { return service.getBySchool(name); }

    // Count by School
    @GetMapping("/school/count")
    public long getSchoolCount(@RequestParam String name) { return service.getCountBySchool(name); }

    // Count by Standard
    @GetMapping("/school/standard/count")
    public long getStdCount(@RequestParam("class") String std) { return service.getCountByStandard(std); }

    // Filter by Result (Pass/Fail)
    @GetMapping("/result")
    public List<Student> getResult(@RequestParam boolean pass) { return service.getByResult(pass); }

    // Strength (Gender + Standard)
    @GetMapping("/strength")
    public long getStrength(@RequestParam String gender, @RequestParam String standard) {
        return service.getStrength(gender, standard);
    }
}