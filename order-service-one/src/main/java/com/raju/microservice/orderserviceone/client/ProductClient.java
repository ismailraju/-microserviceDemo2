package com.raju.microservice.orderserviceone.client;

import com.raju.microservice.orderserviceone.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "product-service-one")
public interface ProductClient {

    @PostMapping("/ids")
    List<Product> getMultiple(List<Long> ids);

}
