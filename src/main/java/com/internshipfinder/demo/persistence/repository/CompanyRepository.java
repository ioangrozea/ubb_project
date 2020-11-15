package com.internshipfinder.demo.persistence.repository;

import com.internshipfinder.demo.persistence.entity.Admin;
import com.internshipfinder.demo.persistence.entity.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    Optional<Company> findCompanyByUsernameAndPassword(String username, String password);
}
