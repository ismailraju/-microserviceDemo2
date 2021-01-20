package com.raju.microservice.resourceserviceone.service;

import com.raju.microservice.resourceserviceone.model.Customer;

import java.util.List;

public interface CustomerService {


    Customer save(Customer customer);

    Customer fetchById(int profileId);

    List<Customer> fetchAllProfiles();
}
