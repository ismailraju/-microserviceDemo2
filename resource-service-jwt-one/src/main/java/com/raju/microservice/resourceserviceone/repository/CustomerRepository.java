package com.raju.microservice.resourceserviceone.repository;


import com.raju.microservice.resourceserviceone.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
