package com.code.screening.worldpayapp.customer;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.code.screening.worldpayapp.customer.domain.CustomerRepository;
import com.code.screening.worldpayapp.customer.dto.Customer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ConversionService conversionService;

    public List<Customer> allCustomers() {
        return customerRepository.findAll()
            .stream()
            .map(user -> conversionService.convert(user, Customer.class))
            .collect(toUnmodifiableList());
    }

}
