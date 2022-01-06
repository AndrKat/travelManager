package com.example.service;

import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.Connection;
import com.example.repository.ConnectionDao;
import com.example.service.api.ConnectionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ConnectionServiceImpl implements ConnectionService {

    private final ConnectionDao connectionDao;

    ConnectionServiceImpl(ConnectionDao connectionDao) {
        this.connectionDao = connectionDao;
    }

    @Override
    @Transactional
    public Connection create(Connection connection) throws IncorrectDataException {
        return connectionDao.save(connection);
    }

    @Transactional
    @Override
    public Connection update(Long id, Connection connectionInput) throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Connection");
        final Connection connectionOutput = connectionDao.findById(id)
                .orElseThrow(() -> new DataWasNotFoundException("Connection"));
        connectionOutput.setTypeOfMovement(connectionInput.getTypeOfMovement());
        connectionOutput.setAvailabilityOfTickets(connectionInput.isAvailabilityOfTickets());
        connectionOutput.setPriceOfTicket(connectionInput.getPriceOfTicket());
        return connectionDao.save(connectionOutput);
    }

    @Override
    public Connection read(Long id) throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Connection");
        final Connection connection = connectionDao.findById(id)
                .orElseThrow(() -> new DataWasNotFoundException("Connection"));
        return connection;
    }

    @Override
    public List<Connection> readAll() {
        return StreamSupport.stream(connectionDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Connection");
        try {
            connectionDao.deleteById(id);
        } catch (Exception e) {
            throw new DataWasNotFoundException("Connection");
        }
    }
}