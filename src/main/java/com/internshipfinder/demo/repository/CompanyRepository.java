package com.internshipfinder.demo.repository;

import com.internshipfinder.demo.entity.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
