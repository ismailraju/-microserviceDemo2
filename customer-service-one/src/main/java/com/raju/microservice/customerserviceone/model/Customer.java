package com.raju.microservice.customerserviceone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO )
//    @SequenceGenerator(name = "CUSTOMER_SEQ", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
    private Long id;
    private String name;
    private CustomerType type;
    @Transient
    private List<Account> accounts = new ArrayList<>();


    public Customer(String name, CustomerType type) {
        this.name = name;
        this.type = type;
    }


}
