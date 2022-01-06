package com.example.repository;

import com.example.model.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripDao extends CrudRepository<Trip, Long> {
}
