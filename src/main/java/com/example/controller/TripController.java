package com.example.controller;

import com.example.dto.TripDto;
import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.Trip;
import com.example.service.api.TripService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TripController {

    private final ObjectMapper objectMapper;
    private final TripService service;


    public TripController(ObjectMapper objectMapper, TripService service) {
        this.objectMapper = objectMapper;
        this.service = service;
    }

    @PostMapping("/trip")
    public TripDto create(@RequestBody TripDto tripDto) throws IncorrectDataException {
        Trip trip = objectMapper.convertValue(tripDto, Trip.class);
        return objectMapper.convertValue(service.create(trip), TripDto.class);
    }

    @PutMapping("/trip")
    public TripDto update(@RequestParam(value = "id") Long id,
                          @RequestBody TripDto tripDto) throws DataWasNotFoundException, IncorrectDataException {
        Trip trip = objectMapper.convertValue(tripDto, Trip.class);
        return objectMapper.convertValue(service.update(id, trip), TripDto.class);
    }

    @GetMapping("/trip/{id}")
    public TripDto read(@PathVariable("id") Long id) throws DataWasNotFoundException, IncorrectDataException {
        return objectMapper.convertValue(service.read(id), TripDto.class);
    }

    @GetMapping("/trips")
    public List<TripDto> readAll() {
        return service.readAll().stream()
                .map(trip -> objectMapper.convertValue(trip, TripDto.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/trip/{id}")
    public void delete(@PathVariable("id") Long id) throws DataWasNotFoundException, IncorrectDataException {
        service.delete(id);
    }
}
