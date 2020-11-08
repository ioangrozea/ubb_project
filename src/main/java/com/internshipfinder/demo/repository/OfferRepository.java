package com.internshipfinder.demo.repository;

import com.internshipfinder.demo.entity.Offer;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<Offer, Long> {
}
