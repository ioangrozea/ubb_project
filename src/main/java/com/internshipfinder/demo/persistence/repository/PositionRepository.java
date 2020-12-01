package com.internshipfinder.demo.persistence.repository;

import com.internshipfinder.demo.persistence.entity.Position;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PositionRepository extends CrudRepository<Position, Long> {
    Set<Position> getAllByCompanyId(Long id);
}
