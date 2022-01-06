package com.example.repository;

import com.example.model.Knot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnotDao extends CrudRepository<Knot, Long> {
}
