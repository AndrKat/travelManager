package com.example.repository;

import com.example.model.Connection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionDao extends CrudRepository<Connection, Long>{
}
