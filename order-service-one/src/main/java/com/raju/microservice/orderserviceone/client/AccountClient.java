package com.raju.microservice.orderserviceone.client;

import com.raju.microservice.orderserviceone.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "account-service-one")
public interface AccountClient {

    @PutMapping("/withdraw/{customerId}/{amount}")
    Account withdraw(@PathVariable("customerId") Long customerId, @PathVariable("amount") int amount);
}
