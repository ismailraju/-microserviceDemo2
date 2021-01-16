package com.raju.microservice.orderserviceone.client;


import com.raju.microservice.orderserviceone.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service-one")
public interface CustomerClient {

    @GetMapping("/withAccounts/{customerId}")
    Customer getwithAccount(@PathVariable("customerId") Long customerId);
}
