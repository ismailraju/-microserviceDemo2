package com.raju.microservice.orderserviceone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private OrderStatus status;
    private int price;
    private Long customerId;
    private Long accountId;

////    @Transient
//    @ElementCollection
//    @CollectionTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id"))
////    @Column(name = "product_ids")
//    private List<Long> productIds;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "order_id")
//    @OneToMany(mappedBy = "order_product")
    private List<OrderProduct> productIds;


}
