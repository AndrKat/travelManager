package com.example.service.api;

import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.Trip;

import java.util.List;

public interface TripService {

    Trip create(Trip trip) throws IncorrectDataException;

    Trip update(Long id, Trip trip) throws DataWasNotFoundException, IncorrectDataException;

    Trip read(Long id) throws DataWasNotFoundException, IncorrectDataException;

    List<Trip> readAll();

    void delete(Long id) throws DataWasNotFoundException, IncorrectDataException;
}
