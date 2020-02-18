package com.code.screening.worldpayapp.customer.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.code.screening.worldpayapp.customer.domain.Customer;

@Component
public class CustomerToDtoConverter implements Converter<Customer, com.code.screening.worldpayapp.customer.dto.Customer> {

	@Override
	public com.code.screening.worldpayapp.customer.dto.Customer convert(Customer source) {
        return com.code.screening.worldpayapp.customer.dto.Customer.builder()
            .id(source.getId())
            .name(source.getName())
            .surname(source.getSurname())
            .build();
	}

}
