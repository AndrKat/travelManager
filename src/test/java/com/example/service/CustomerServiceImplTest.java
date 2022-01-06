package com.example.service;

import com.example.exception.IncorrectDataException;
import com.example.model.Customer;
import com.example.repository.CustomerDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CustomerServiceImpl service;

    @Test
    void whenCreateReturnCustomer() throws IncorrectDataException {
        Customer customer = getFilledCustomer();
        Mockito.when(customerDao.save(Mockito.any())).thenReturn(customer);
        Customer expected = service.create(customer);
        Customer actual = customer;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(customerDao, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void whenCreate_throwException() {
        Customer customer = getEmptyCustomer();
        IncorrectDataException exception = Assertions
                .assertThrows(IncorrectDataException.class, () -> service.create(customer));
        Assertions.assertEquals(exception.getMessage(), "Customer: data is not correct");
        Mockito.verify(customerDao, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    void update() {
    }

    @Test
    void read() {
    }

    @Test
    void readAll() {
    }

    @Test
    void delete() {
    }

    private Customer getEmptyCustomer() {
        return new Customer();
    }

    private Customer getFilledCustomer() {
        Customer customer = getEmptyCustomer();
        customer.setNameOfCustomer("TestName");
        customer.setEmail("Test@gmail.com");
        customer.setPassword("Password");
        return customer;
    }
}