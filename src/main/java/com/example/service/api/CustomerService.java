package com.example.service.api;

import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer create(Customer customer) throws IncorrectDataException;

    Customer update(Long id, Customer customer) throws DataWasNotFoundException, IncorrectDataException;

    Customer read(Long id) throws DataWasNotFoundException, IncorrectDataException;

    List<Customer> readAll();

    void delete(Long id) throws DataWasNotFoundException, IncorrectDataException;

}
