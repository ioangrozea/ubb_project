package com.internshipfinder.demo.repository;

import com.internshipfinder.demo.entity.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {
}
