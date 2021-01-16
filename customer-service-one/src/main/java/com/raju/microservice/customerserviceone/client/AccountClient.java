package com.raju.microservice.customerserviceone.client;

import com.raju.microservice.customerserviceone.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "account-service-one"
//        ,url = "localhost:8080/account-one/"
)
public interface AccountClient {


    @GetMapping("/customer/{customerId}")
    List<Account> getAccountsbyCustomer(@PathVariable("customerId") Long customerId);

}
