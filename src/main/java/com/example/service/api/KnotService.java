package com.example.service.api;

import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.Knot;

import java.util.List;

public interface KnotService {

    Knot create(Knot knot) throws IncorrectDataException;

    Knot update(Long id, Knot knot) throws DataWasNotFoundException, IncorrectDataException;

    Knot read(Long id) throws DataWasNotFoundException, IncorrectDataException;

    List<Knot> readAll();

    void delete(Long id) throws DataWasNotFoundException, IncorrectDataException;
}
