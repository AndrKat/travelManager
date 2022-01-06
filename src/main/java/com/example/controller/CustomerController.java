package com.example.controller;

import com.example.dto.CustomerDto;
import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.Customer;
import com.example.service.api.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    private final ObjectMapper objectMapper;
    private final CustomerService service;


    public CustomerController(ObjectMapper objectMapper, CustomerService service) {
        this.objectMapper = objectMapper;
        this.service = service;
    }

    @PostMapping("/customer")
    public CustomerDto create(@RequestBody CustomerDto customerDto) throws IncorrectDataException {
        Customer customer = objectMapper.convertValue(customerDto, Customer.class);
        return objectMapper.convertValue(service.create(customer), CustomerDto.class);
    }

    @PutMapping("/customer")
    public CustomerDto update(@RequestParam("id") Long id,
                              @RequestBody CustomerDto customerDto) throws DataWasNotFoundException, IncorrectDataException {
        Customer customer = objectMapper.convertValue(customerDto, Customer.class);
        return objectMapper.convertValue(service.update(id, customer), CustomerDto.class);
    }

    @GetMapping("/customer/{id}")
    public CustomerDto read(@PathVariable("id") Long id) throws DataWasNotFoundException, IncorrectDataException {
        return objectMapper.convertValue(service.read(id), CustomerDto.class);
    }

    @GetMapping("/customers")
    public List<CustomerDto> readAll() {
        return service.readAll().stream()
                .map(customer -> objectMapper.convertValue(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/customer/{id}")
    public void delete(@PathVariable("id") Long id) throws DataWasNotFoundException, IncorrectDataException {
        service.delete(id);
    }
}
