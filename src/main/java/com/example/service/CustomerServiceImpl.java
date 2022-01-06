package com.example.service;

import com.example.exception.DataWasNotFoundException;
import com.example.exception.IncorrectDataException;
import com.example.model.Customer;
import com.example.repository.CustomerDao;
import com.example.service.api.CustomerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    @Transactional
    public Customer create(Customer customer) throws IncorrectDataException {
//        if ((customer.getNameOfCustomer() == "") || (customer.getPassword() == "")
//                || (customer.getEmail() == "")) {
//            customer.setNameOfCustomer("Traveler");
//            customer.setEmail("Traveler@gmail.com");
//            customer.setPassword("1111");
//        }
        return customerDao.save(customer);
    }

    @Override
    @Transactional
    public Customer update(Long id, Customer customerInput) throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Customer");
        final Customer customerOutput = customerDao.findById(id)
                .orElseThrow(() -> new DataWasNotFoundException("Customer"));
        customerOutput.setNameOfCustomer(customerInput.getNameOfCustomer());
        customerOutput.setEmail(customerInput.getEmail());
        customerOutput.setPassword(customerInput.getPassword());
        return customerDao.save(customerOutput);
    }

    @Override
    public Customer read(Long id) throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Customer");
        final Customer customer = customerDao.findById(id)
                .orElseThrow(() -> new DataWasNotFoundException("Customer"));
        return customer;
    }

    @Override
    public List<Customer> readAll() {
        return StreamSupport.stream(customerDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) throws DataWasNotFoundException, IncorrectDataException {
        if (id < 1) throw new IncorrectDataException("Customer");
        try {
            customerDao.deleteById(id);
        } catch (Exception e) {
            throw new DataWasNotFoundException("Customer");
        }
    }
}
