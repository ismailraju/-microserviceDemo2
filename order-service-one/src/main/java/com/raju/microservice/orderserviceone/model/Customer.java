package com.raju.microservice.orderserviceone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

	private Long id;
	private String name;
	private CustomerType type;
	private List<Account> accounts = new ArrayList<>();


}
