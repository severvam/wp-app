package com.code.screening.worldpayapp.offer.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.code.screening.worldpayapp.customer.domain.CustomerRepository;

import lombok.AllArgsConstructor;

import static java.util.Optional.ofNullable;

@AllArgsConstructor
class CustomerValidator implements ConstraintValidator<CustomerConstraint, Long> {

    private final CustomerRepository customerRepository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return ofNullable(id)
                .map(customerId -> customerRepository.findById(id).isPresent())
                .orElse(true);
    }

}
