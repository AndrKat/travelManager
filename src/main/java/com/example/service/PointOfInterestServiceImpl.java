package com.example.service;

import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.PointOfInterest;
import com.example.repository.PointOfInterestDao;
import com.example.service.api.PointOfInterestService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PointOfInterestServiceImpl implements PointOfInterestService {

    private final PointOfInterestDao pointOfInterestDao;

    PointOfInterestServiceImpl(PointOfInterestDao pointOfInterestDao) {
        this.pointOfInterestDao = pointOfInterestDao;
    }

    @Override
    @Transactional
    public PointOfInterest create(PointOfInterest pointOfInterest) throws IncorrectDataException {
        return pointOfInterestDao.save(pointOfInterest);
    }

    @Override
    @Transactional
    public PointOfInterest update(Long id, PointOfInterest pointInput)
            throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Point of interest");
        final PointOfInterest pointOutput = pointOfInterestDao.findById(id)
                .orElseThrow(() -> new DataWasNotFoundException("Point of interest"));
        pointOutput.setNameOfPlace(pointInput.getNameOfPlace());
        pointOutput.setCoordinates(pointInput.getCoordinates());
        pointOutput.setDescription(pointInput.getDescription());
        return pointOfInterestDao.save(pointOutput);
    }

    @Override
    public PointOfInterest read(Long id) throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Point of interest");
        final PointOfInterest pointOfInterest = pointOfInterestDao.findById(id)
                .orElseThrow(() -> new DataWasNotFoundException("Point of interest"));
        return pointOfInterest;
    }

    @Override
    public List<PointOfInterest> readAll() {
        return StreamSupport.stream(pointOfInterestDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Point of interest");
        try {
            pointOfInterestDao.deleteById(id);
        } catch (Exception e) {
            throw new DataWasNotFoundException("Point of interest");
        }
    }
}
