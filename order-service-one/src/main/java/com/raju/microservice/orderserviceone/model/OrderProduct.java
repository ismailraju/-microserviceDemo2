package com.raju.microservice.orderserviceone.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OrderProduct {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_PRODUCT_SEQ")
    @SequenceGenerator(name = "ORDER_PRODUCT_SEQ", sequenceName = "ORDER_PRODUCT_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "product_id")
    private Long productId;


}
