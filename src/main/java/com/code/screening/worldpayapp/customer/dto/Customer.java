package com.code.screening.worldpayapp.customer.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Customer {

	private final Long id;
	private final String name;
	private final String surname;

}
