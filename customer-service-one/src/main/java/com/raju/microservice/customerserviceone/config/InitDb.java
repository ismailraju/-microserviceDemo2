package com.raju.microservice.customerserviceone.config;

import com.raju.microservice.customerserviceone.model.Customer;
import com.raju.microservice.customerserviceone.model.CustomerType;
import com.raju.microservice.customerserviceone.repository.CustomerRepository;
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

        customerRepository.save(new Customer("John Scott", CustomerType.NEW));
        customerRepository.save(new Customer("Adam Smith", CustomerType.REGULAR));
        customerRepository.save(new Customer("Jacob Ryan", CustomerType.VIP));

    }
}
