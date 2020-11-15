package com.internshipfinder.demo.persistence.repository;

import com.internshipfinder.demo.persistence.entity.Position;
import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<Position, Long> {
}
