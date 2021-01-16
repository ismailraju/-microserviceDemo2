package com.raju.microservice.productserviceone.config;

import com.raju.microservice.productserviceone.model.Product;
import com.raju.microservice.productserviceone.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
public class InitDB implements CommandLineRunner {

    private ProductRepository productRepository;

    public InitDB(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.deleteAll();

        productRepository.save(new Product("Test1", 1000));
        productRepository.save(new Product("Test2", 1500));
        productRepository.save(new Product("Test3", 2000));
        productRepository.save(new Product("Test4", 3000));
        productRepository.save(new Product("Test5", 1300));
        productRepository.save(new Product("Test6", 2700));
        productRepository.save(new Product("Test7", 3500));
        productRepository.save(new Product("Test8", 1250));
        productRepository.save(new Product("Test9", 2450));
        productRepository.save(new Product("Test10", 800));

    }
}
