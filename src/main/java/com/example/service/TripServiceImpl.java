package com.example.service;

import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.Trip;
import com.example.repository.TripDao;
import com.example.service.api.TripService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TripServiceImpl implements TripService {

    private final TripDao tripDao;

    TripServiceImpl(TripDao tripDao) {
        this.tripDao = tripDao;
    }


    @Override
    @Transactional
    public Trip create(Trip trip) throws IncorrectDataException {
        return tripDao.save(trip);
    }

    @Override
    @Transactional
    public Trip update(Long id, Trip tripInput) throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Trip");
        final Trip tripOutput = tripDao.findById(id)
                .orElseThrow(() -> new DataWasNotFoundException("Trip"));
        tripOutput.setNameOfTrip(tripInput.getNameOfTrip());
        tripOutput.setStartOfTrip(tripInput.getStartOfTrip());
        tripOutput.setFinishOfTrip(tripInput.getFinishOfTrip());
        return tripDao.save(tripOutput);
    }

    @Override
    public Trip read(Long id) throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Trip");
        final Trip trip = tripDao.findById(id)
                .orElseThrow(() -> new DataWasNotFoundException("Trip"));
        return trip;
    }

    @Override
    public List<Trip> readAll() {
        return StreamSupport.stream(tripDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Trip");
        try {
            tripDao.deleteById(id);
        } catch (Exception e) {
            throw new DataWasNotFoundException("Trip");
        }
    }
}
