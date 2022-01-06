package com.example.service.api;

import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.Connection;

import java.util.List;

public interface ConnectionService {

    Connection create(Connection connection) throws IncorrectDataException;

    Connection update(Long id, Connection connection) throws DataWasNotFoundException, IncorrectDataException;

    Connection read(Long id) throws DataWasNotFoundException, IncorrectDataException;

    List<Connection> readAll();

    void delete(Long id) throws DataWasNotFoundException, IncorrectDataException;
}
