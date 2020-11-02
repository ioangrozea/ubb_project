package com.internshipfinder.demo.repository;

import com.internshipfinder.demo.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
