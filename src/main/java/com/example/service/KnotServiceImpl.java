package com.example.service;

import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.Knot;
import com.example.repository.KnotDao;
import com.example.service.api.KnotService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class KnotServiceImpl implements KnotService {

    private final KnotDao knotDao;

    KnotServiceImpl(KnotDao knotDao) {
        this.knotDao = knotDao;
    }

    @Override
    @Transactional
    public Knot create(Knot knot) throws IncorrectDataException {
        return knotDao.save(knot);
    }

    @Override
    @Transactional
    public Knot update(Long id, Knot knotInput) throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Knot");
        final Knot knotOutput = knotDao.findById(id)
                .orElseThrow(() -> new DataWasNotFoundException("Knot"));
        knotOutput.setPlaceOfTransfer(knotInput.getPlaceOfTransfer());
        knotOutput.setDateOfTransfer(knotInput.getDateOfTransfer());
        knotOutput.setNumbersOfDays(knotInput.getNumbersOfDays());
        return knotDao.save(knotOutput);
    }

    @Override
    public Knot read(Long id) throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Knot");
        final Knot knot = knotDao.findById(id)
                .orElseThrow(() -> new DataWasNotFoundException("Knot"));
        return knot;
    }

    @Override
    public List<Knot> readAll() {
        return StreamSupport.stream(knotDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Knot");
        try {
            knotDao.deleteById(id);
        } catch (Exception e) {
            throw new DataWasNotFoundException("Knot");
        }
    }
}
