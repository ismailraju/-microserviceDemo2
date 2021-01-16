package com.raju.microservice.productserviceone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raju.microservice.productserviceone.model.Product;
import com.raju.microservice.productserviceone.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductRepository productRepository;
    private ObjectMapper objectMapper = new ObjectMapper();


    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @PostMapping
    public Product add(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable("id") Long id) throws Exception {
        Optional<Product> prod = productRepository.findById(id);

        if (prod.isPresent()) {
            LOGGER.info("Product found {}", objectMapper.writeValueAsString(prod.get()));
            return prod.get();
        } else {
            LOGGER.info("Product not found {}", objectMapper.writeValueAsString(Collections.singletonMap("id", id)));
            throw new Exception("Product not found.");
        }

    }

    @PostMapping("/ids")
    public List<Product> getMultiple(@RequestBody List<Long> ids) {
        return productRepository.findAllById(ids);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
    }


}
