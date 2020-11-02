package com.internshipfinder.demo.repository;

import com.internshipfinder.demo.entity.StudentProfile;
import org.springframework.data.repository.CrudRepository;

public interface StudentProfileRepository extends CrudRepository<StudentProfile, Long> {
}
