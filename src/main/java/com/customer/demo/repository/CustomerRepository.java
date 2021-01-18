package com.customer.demo.repository;

import com.customer.demo.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


public interface CustomerRepository  extends JpaRepository<Customer, Serializable> {
    Page<Customer> findByFirstNameContaining(String firstName, Pageable pageable);
}
