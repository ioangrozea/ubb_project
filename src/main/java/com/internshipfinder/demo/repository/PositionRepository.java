package com.internshipfinder.demo.repository;

import com.internshipfinder.demo.entity.Position;
import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<Position, Long> {
}
