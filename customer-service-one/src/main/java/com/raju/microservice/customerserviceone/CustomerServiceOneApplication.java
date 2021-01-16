package com.raju.microservice.customerserviceone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CustomerServiceOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceOneApplication.class, args);
    }

}
