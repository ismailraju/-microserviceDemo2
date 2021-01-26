package com.raju.microservice.resourceserviceone.config;

import com.raju.microservice.resourceserviceone.model.Customer;
import com.raju.microservice.resourceserviceone.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDb implements CommandLineRunner {
    private CustomerRepository customerRepository;

    public InitDb(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        customerRepository.deleteAll();

        customerRepository.save(Customer.builder().firstName("raju").lastName("ismail")
                .dlNumber("100")
                .zipcode("1206")
                .build());

        customerRepository.save(Customer.builder().firstName("shakil").lastName("max")
                .dlNumber("1100")
                .zipcode("1106")
                .build());

    }
}
