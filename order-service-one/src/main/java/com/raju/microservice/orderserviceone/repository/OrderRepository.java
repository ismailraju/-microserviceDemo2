package com.raju.microservice.orderserviceone.repository;

import com.raju.microservice.orderserviceone.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
