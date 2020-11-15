package com.internshipfinder.demo.persistence.repository;

import com.internshipfinder.demo.persistence.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findStudentByUsernameAndPassword(String username, String password);
}
