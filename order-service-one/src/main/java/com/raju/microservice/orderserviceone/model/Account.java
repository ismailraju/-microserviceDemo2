package com.raju.microservice.orderserviceone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {

	private Long id;
	private String number;
	private int balance;


}
