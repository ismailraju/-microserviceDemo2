package com.raju.microservice.orderserviceone.config;

import com.raju.microservice.orderserviceone.model.Order;
import com.raju.microservice.orderserviceone.model.OrderProduct;
import com.raju.microservice.orderserviceone.model.OrderStatus;
import com.raju.microservice.orderserviceone.model.Product;
import com.raju.microservice.orderserviceone.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class InitDb implements CommandLineRunner {

    private OrderRepository orderRepository;

    public InitDb(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        orderRepository.deleteAll();


        orderRepository.save(Order.builder().accountId(1L).customerId(1L).price(10).status(OrderStatus.ACCEPTED)
                .productIds(Arrays.asList(
                        OrderProduct.builder().productId(1L).build(),
                        OrderProduct.builder().productId(2L).build()
                ))
                .build());
        orderRepository.save(Order.builder().accountId(1L).customerId(2L).price(15).status(OrderStatus.REJECTED)
                .productIds(Arrays.asList(
                        OrderProduct.builder().productId(1L).build(),
                        OrderProduct.builder().productId(2L).build()
                ))
                .build());

    }
}
