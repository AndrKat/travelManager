package com.example.controller;

import com.example.dto.ConnectionDto;
import com.example.dto.CustomerDto;
import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.Connection;
import com.example.service.api.ConnectionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ConnectionController {

    private final ObjectMapper objectMapper;
    private final ConnectionService service;

    ConnectionController(ObjectMapper objectMapper, ConnectionService service) {
        this.objectMapper = objectMapper;
        this.service = service;
    }

    @PostMapping("/connection")
    public ConnectionDto create(@RequestBody ConnectionDto connectionDto) throws IncorrectDataException {
        Connection connection = objectMapper.convertValue(connectionDto, Connection.class);
        return objectMapper.convertValue(service.create(connection), ConnectionDto.class);
    }

    @PutMapping("/connection")
    public ConnectionDto update(@RequestParam(value = "id") Long id,
                                @RequestBody ConnectionDto connectionDto)
            throws DataWasNotFoundException, IncorrectDataException {
        Connection connection = objectMapper.convertValue(connectionDto, Connection.class);
        return objectMapper.convertValue(service.update(id, connection), ConnectionDto.class);
    }

    @GetMapping("/connection/{id}")
    public ConnectionDto read(@PathVariable("id") Long id) throws DataWasNotFoundException, IncorrectDataException {
        return objectMapper.convertValue(service.read(id), ConnectionDto.class);
    }

    @GetMapping("/connections")
    public List<ConnectionDto> readAll() {
        return service.readAll().stream()
                .map(connection -> objectMapper.convertValue(connection, ConnectionDto.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("connection/{id}")
    public void delete(@PathVariable("id") Long id) throws DataWasNotFoundException, IncorrectDataException {
        service.delete(id);
    }
}
