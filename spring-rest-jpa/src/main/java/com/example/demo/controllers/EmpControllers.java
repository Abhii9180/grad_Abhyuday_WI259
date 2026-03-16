package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entities.Employee;
import com.example.demo.repo.EmpRepo;

@RestController // Logic: Combines @Controller and @ResponseBody
@RequestMapping("/api") // Good practice: prefix all API calls
public class EmpControllers {

    @Autowired
    EmpRepo repo;

    // 1. READ ALL
    @GetMapping("/employees")
    public List<Employee> getAll() {
        return repo.findAll(); // Returns a JSON array
    }

    // 2. READ ONE (Using Path Variable)
    @GetMapping("/employees/{id}")
    public Optional<Employee> getOne(@PathVariable int id) {
        // Optional logic: It's a container that might be empty. 
        // Prevents the "NullPointerException" crash!
        return repo.findById(id);
    }

    // 3. INSERT (Using RequestBody)
    @PostMapping("/employees")
    public String addEmployee(@RequestBody Employee e) {
        if (repo.existsById(e.getId())) {
            return "Error: Employee with ID " + e.getId() + " already exists.";
        }
        repo.save(e);
        return "Successfully added " + e.getName();
    }

    // 4. UPDATE (Using Path Variable + RequestBody)
    @PutMapping("/employees/{id}")
    public String updateEmployee(@PathVariable int id, @RequestBody Employee e) {
        if (!repo.existsById(id)) {
            return "Error: Employee not found.";
        }
        // Safety check: ensure the JSON ID matches the URL ID
        e.setId(id); 
        repo.save(e);
        return "Successfully updated record for ID: " + id;
    }

    // 5. DELETE
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Deleted employee with ID: " + id;
        }
        return "ID not found. Nothing deleted.";
    }

    // 6. SEARCH (Using Query Parameter)
    // URL: /api/employees/search?role=Manager
 //   @GetMapping("/employees/search")
//    public List<Employee> searchByRole(@RequestParam String role) {
//        return repo.findByDesignation(role);
//    }
}