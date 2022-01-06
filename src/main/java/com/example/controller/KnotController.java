package com.example.controller;

import com.example.dto.KnotDto;
import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.Knot;
import com.example.service.api.KnotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class KnotController {

    private final ObjectMapper objectMapper;
    private final KnotService service;

    KnotController(ObjectMapper objectMapper, KnotService service) {
        this.objectMapper = objectMapper;
        this.service = service;
    }

    @PostMapping("/knot")
    public KnotDto create(@RequestBody KnotDto knotDto) throws IncorrectDataException {
        Knot knot = objectMapper.convertValue(knotDto, Knot.class);
        return objectMapper.convertValue(service.create(knot), KnotDto.class);
    }

    @PutMapping("/knot")
    public KnotDto update(@RequestParam(value = "id") Long id,
                          @RequestBody KnotDto knotDto) throws DataWasNotFoundException, IncorrectDataException {
        Knot knot = objectMapper.convertValue(knotDto, Knot.class);
        return objectMapper.convertValue(service.update(id, knot), KnotDto.class);
    }

    @GetMapping("/knot/{id}")
    public KnotDto read(@PathVariable("id") Long id) throws DataWasNotFoundException, IncorrectDataException {
        return objectMapper.convertValue(service.read(id), KnotDto.class);
    }

    @GetMapping("/knots")
    public List<KnotDto> readAll() {
        return service.readAll().stream()
                .map(knot -> objectMapper.convertValue(knot, KnotDto.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/knot/{id}")
    public void delete(@PathVariable("id") Long id) throws DataWasNotFoundException, IncorrectDataException {
        service.delete(id);
    }
}
