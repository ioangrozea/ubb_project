package com.internshipfinder.demo.persistence.repository;

import com.internshipfinder.demo.persistence.entity.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, Long> {
    Optional<Admin> findAdminByUsernameAndPassword(String username, String password);
}
