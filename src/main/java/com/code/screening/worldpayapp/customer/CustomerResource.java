package com.code.screening.worldpayapp.customer;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.screening.worldpayapp.customer.dto.Customer;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/customers", produces = APPLICATION_JSON_VALUE)
public class CustomerResource {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> customers() {
        return customerService.allCustomers();
    }

}
