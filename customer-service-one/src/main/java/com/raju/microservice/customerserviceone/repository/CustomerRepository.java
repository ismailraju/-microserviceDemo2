package com.raju.microservice.customerserviceone.repository;

import com.raju.microservice.customerserviceone.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
