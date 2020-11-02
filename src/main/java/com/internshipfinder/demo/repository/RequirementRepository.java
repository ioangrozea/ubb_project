package com.internshipfinder.demo.repository;

import com.internshipfinder.demo.entity.Requirement;
import org.springframework.data.repository.CrudRepository;

public interface RequirementRepository extends CrudRepository<Requirement, Long> {
}
