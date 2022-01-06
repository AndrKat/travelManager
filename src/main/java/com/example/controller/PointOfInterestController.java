package com.example.controller;

import com.example.dto.PointOfInterestDto;
import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.PointOfInterest;
import com.example.service.api.PointOfInterestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PointOfInterestController {

    private final ObjectMapper objectMapper;
    private final PointOfInterestService service;

    PointOfInterestController(ObjectMapper objectMapper, PointOfInterestService service) {
        this.objectMapper = objectMapper;
        this.service = service;
    }

    @PostMapping("/pointOfInterest")
    public PointOfInterestDto create(@RequestBody PointOfInterestDto pointOfInterestDto)
            throws IncorrectDataException {
        PointOfInterest pointOfInterest = objectMapper.convertValue(pointOfInterestDto, PointOfInterest.class);
        return objectMapper.convertValue(service.create(pointOfInterest), PointOfInterestDto.class);
    }

    @PutMapping("/pointOfInterest")
    public PointOfInterestDto update(@RequestParam("id") Long id,
                                     @RequestBody PointOfInterestDto pointOfInterestDto)
            throws DataWasNotFoundException, IncorrectDataException {
        PointOfInterest pointOfInterest = objectMapper.convertValue(pointOfInterestDto, PointOfInterest.class);
        return objectMapper.convertValue(service.update(id, pointOfInterest), PointOfInterestDto.class);
    }

    @GetMapping("/pointOfInterest/{id}")
    public PointOfInterestDto read(@PathVariable("id") Long id)
            throws DataWasNotFoundException, IncorrectDataException {
        return objectMapper.convertValue(service.read(id), PointOfInterestDto.class);
    }

    @GetMapping("/pointsOfInterest")
    public List<PointOfInterestDto> readAll() {
        return service.readAll().stream()
                .map(pointOfInterest -> objectMapper.convertValue(pointOfInterest, PointOfInterestDto.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/pointOfInterest/{id}")
    public void delete(@PathVariable("id") Long id) throws DataWasNotFoundException, IncorrectDataException{
        service.delete(id);
    }
}
