package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Must match service.getBySchool()
    List<Student> findBySchool(String school);

    // Must match service.getCountBySchool()
    long countBySchool(String school);

    // Must match service.getCountByStandard()
    long countByStandard(String standard);

    // Must match service.getStrength()
    long countByGenderAndStandard(String gender, String standard);

    // Must match service.getByResult(true)
    List<Student> findByPercentageGreaterThanEqualOrderByPercentageDesc(Double percentage);

    // Must match service.getByResult(false)
    List<Student> findByPercentageLessThanOrderByPercentageDesc(Double percentage);
}