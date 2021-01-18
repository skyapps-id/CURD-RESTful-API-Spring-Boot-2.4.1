package com.customer.demo.service;

import com.customer.demo.model.Customer;
import com.customer.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public Page<Customer> fetchAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Transactional
    public Page<Customer> findByFirstNameContaining(String firstName, Pageable pageable) {
        return customerRepository.findByFirstNameContaining(firstName, pageable);
    }

    @Transactional
    public Customer fetchById(int customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            return null;
        }
    }

    @Transactional
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public void delete(int customerId) {
        customerRepository.deleteById(customerId);
    }

}
