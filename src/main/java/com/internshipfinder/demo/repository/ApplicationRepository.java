package com.internshipfinder.demo.repository;

import com.internshipfinder.demo.entity.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Application.ApplicationPK> {
}
