package com.raju.accountserviceone.accountserviceone.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
    @SequenceGenerator(name = "ACCOUNT_SEQ", sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
    private Long id;


    private String number;

    private int balance;

    private Long customerId;

    public Account(String number, int balance, Long customerId) {
        this.number = number;
        this.balance = balance;
        this.customerId = customerId;
    }

}
