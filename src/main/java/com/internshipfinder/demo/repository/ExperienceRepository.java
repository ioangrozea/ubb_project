package com.internshipfinder.demo.repository;

import com.internshipfinder.demo.entity.Experience;
import org.springframework.data.repository.CrudRepository;

public interface ExperienceRepository extends CrudRepository<Experience, Long> {
}
