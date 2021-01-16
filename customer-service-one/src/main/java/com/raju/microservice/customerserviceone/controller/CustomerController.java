package com.raju.microservice.customerserviceone.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.raju.microservice.customerserviceone.client.AccountClient;
import com.raju.microservice.customerserviceone.model.Account;
import com.raju.microservice.customerserviceone.model.Customer;
import com.raju.microservice.customerserviceone.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);


    private CustomerRepository customerRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    private AccountClient accountClient;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, AccountClient accountClient) {
        this.customerRepository = customerRepository;
        this.accountClient = accountClient;
    }


    @PostMapping
    public Customer add(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable("id") Long id) throws Exception {

        Optional<Customer> cust = customerRepository.findById(id);

        if (cust.isPresent()) {
            LOGGER.info("Customer Found : {}", objectMapper.writeValueAsString(cust));
            return cust.get();
        } else {
            LOGGER.info("Customer not Found : {}", objectMapper.writeValueAsString(Collections.singletonMap("id", id)));
            throw new Exception("Customer not Found.");
        }
    }

    @GetMapping("/withAccounts/{id}")
    public Customer getWithAccount(@PathVariable("id") Long id) throws Exception {
        List<Account> accounts = accountClient.getAccountsbyCustomer(id);
        LOGGER.info("Accounts found: {}", objectMapper.writeValueAsString(accounts));
        Customer customer = get(id);

        customer.setAccounts(accounts);
        return customer;
    }

    @PostMapping("/ids")
    public List<Customer> getMultileCustomer(@RequestBody List<Long> ids) {
        return customerRepository.findAllById(ids);

    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {

        customerRepository.deleteById(id);
    }
}
