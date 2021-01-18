package com.customer.demo.controller;

import com.customer.demo.exception.RecordNotFoundException;
import com.customer.demo.model.Customer;
import com.customer.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping(value = "/api")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/customer")
    @ResponseBody
    public Customer save(@Valid @RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping("/customers")
    public ResponseEntity<Map<String, Object>> findByFirstNameIsContaining(
            @RequestParam(required = false) @Size(max = 10) String query,
            @RequestParam(defaultValue = "1") @Min(message = "Minimum Page 1", value = 1) int  page,
            @RequestParam(defaultValue = "10") @Max(message = "Maximum Page 50", value = 50) int  limit ) {
        try {
            int offset = (page - 1) * limit;
            Pageable paging = PageRequest.of(offset, limit);
            List<Customer> customer = new ArrayList<Customer>();
            Page<Customer> pageTuts;

            if (query == null) {
                pageTuts = customerService.fetchAllCustomers(paging);
            } else {
                pageTuts = customerService.findByFirstNameContaining(query, paging);
            }

            customer = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            response.put("customer", customer);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/{id}")
    public Customer fetch(@PathVariable(name = "id") int id) {
        Customer customer = customerService.fetchById(id);
        if (customer == null) {
            throw new RecordNotFoundException("No value present");
        }
        return customer;
    }

    @PutMapping("/customer")
    @ResponseBody
    public Customer updateCustomer(@Valid @RequestBody Customer customer) {
        return customerService.update(customer);
    }

    @DeleteMapping("/customer/{id}")
    public String delete(@PathVariable int id) {
        customerService.delete(id);
        return "Record deleted succesfully";
    }

}
