package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // THIS IS THE METHOD THE ERROR IS TALKING ABOUT
    public List<Student> getAll() { 
        return repository.findAll(); 
    }

    public Optional<Student> getByRegNo(Long regNo) { 
        return repository.findById(regNo); 
    }

    public Student save(Student s) { 
        return repository.save(s); 
    }

    public void delete(Long regNo) { 
        repository.deleteById(regNo); 
    }

    public List<Student> getBySchool(String school) { 
        return repository.findBySchool(school); 
    }

    public long getCountBySchool(String school) { 
        return repository.countBySchool(school); 
    }

    public long getCountByStandard(String std) { 
        return repository.countByStandard(std); 
    }

    public List<Student> getByResult(boolean pass) {
        if (pass) {
            return repository.findByPercentageGreaterThanEqualOrderByPercentageDesc(40.0);
        } else {
            return repository.findByPercentageLessThanOrderByPercentageDesc(40.0);
        }
    }

    public long getStrength(String gender, String standard) {
        return repository.countByGenderAndStandard(gender, standard);
    }

    public Student patchStudent(Long regNo, Student updates) {
        Student existing = repository.findById(regNo)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        
        if (updates.getName() != null) existing.setName(updates.getName());
        if (updates.getStandard() != null) existing.setStandard(updates.getStandard());
        if (updates.getSchool() != null) existing.setSchool(updates.getSchool());
        if (updates.getGender() != null) existing.setGender(updates.getGender());
        if (updates.getPercentage() != null) existing.setPercentage(updates.getPercentage());
        
        return repository.save(existing);
    }
}