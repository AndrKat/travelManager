package com.example.service.api;

import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.PointOfInterest;

import java.util.List;

public interface PointOfInterestService {

    PointOfInterest create(PointOfInterest pointOfInterest) throws IncorrectDataException;

    PointOfInterest update(Long id, PointOfInterest pointOfInterest)
            throws DataWasNotFoundException, IncorrectDataException;

    PointOfInterest read(Long id) throws DataWasNotFoundException, IncorrectDataException;

    List<PointOfInterest> readAll();

    void delete(Long id) throws DataWasNotFoundException, IncorrectDataException;
}
