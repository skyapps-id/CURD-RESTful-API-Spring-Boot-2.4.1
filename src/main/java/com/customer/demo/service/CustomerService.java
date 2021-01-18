package com.customer.demo.service;

import com.customer.demo.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer);

    Page<Customer> fetchAllCustomers(Pageable pageable);

    Page<Customer> findByFirstNameContaining(String firstName, Pageable pageable);

    Customer fetchById(int customerId);

    Customer update(Customer customer);

    void delete(int customerId);


}
