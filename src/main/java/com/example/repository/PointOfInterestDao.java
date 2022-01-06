package com.example.repository;

import com.example.model.PointOfInterest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointOfInterestDao extends CrudRepository<PointOfInterest, Long> {
}
