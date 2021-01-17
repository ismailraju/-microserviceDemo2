package com.raju.microservice.orderserviceone.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raju.microservice.orderserviceone.client.AccountClient;
import com.raju.microservice.orderserviceone.client.CustomerClient;
import com.raju.microservice.orderserviceone.client.ProductClient;
import com.raju.microservice.orderserviceone.model.*;
import com.raju.microservice.orderserviceone.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    public static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private OrderRepository orderRepository;

    private AccountClient accountClient;

    private CustomerClient customerClient;

    private ProductClient productClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    public OrderController(OrderRepository orderRepository, AccountClient accountClient, CustomerClient customerClient, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.accountClient = accountClient;
        this.customerClient = customerClient;
        this.productClient = productClient;
    }


    @PostMapping
    public Order prepare(@RequestBody Order order) throws JsonProcessingException {
        int price = 0;
        List<Product> products = productClient.getMultiple(order.getProductIds()
                .stream()
                .map(OrderProduct::getProductId)
                .collect(Collectors.toList())
        );
        LOGGER.info("Product found: {}", objectMapper.writeValueAsString(products));
        Customer customer = customerClient.getwithAccount(order.getCustomerId());
        LOGGER.info("Customer found: {}", objectMapper.writeValueAsString(customer));

        for (Product p : products) {
            price += p.getPrice();
        }
        final int priceFinal = price;
        Optional<Account> acnt = customer.getAccounts().stream().filter(a -> (a.getBalance() >= priceFinal)).findFirst();

        if (acnt.isPresent()) {
            order.setAccountId(acnt.get().getId());
            order.setStatus(OrderStatus.ACCEPTED);
            order.setPrice(priceFinal);
            LOGGER.info("Account found: {}", objectMapper.writeValueAsString(acnt.get()));
        } else {
            order.setStatus(OrderStatus.REJECTED);
            LOGGER.info("Account not found: {}", objectMapper.writeValueAsString(customer.getAccounts()));
        }

        return orderRepository.save(order);
    }


    @PutMapping("/{id}")
    public Order accept(@PathVariable("id") Long id) throws Exception {

        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {

            LOGGER.info("Order found : {}", objectMapper.writeValueAsString(order));

            accountClient.withdraw(order.get().getAccountId(), order.get().getPrice());

            HashMap<String, Object> log = new HashMap<>();
            log.put("accountId", order.get().getAccountId());
            log.put("price", order.get().getPrice());
            LOGGER.info("Account modified: {}", objectMapper.writeValueAsString(log));

            order.get().setStatus(OrderStatus.DONE);
            LOGGER.info("Order status changed: {}", objectMapper.writeValueAsString(Collections.singletonMap("status", order.get().getStatus())));
            orderRepository.save(order.get());
            return order.get();


        } else {
            LOGGER.info("Order not found : {}", objectMapper.writeValueAsString(order));
            throw new Exception("Order not Found");
        }


    }


    @PutMapping
    public Order update(@RequestBody Order order) {
        return orderRepository.save(order);
    }


}
