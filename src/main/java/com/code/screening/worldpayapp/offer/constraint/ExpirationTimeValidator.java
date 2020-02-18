package com.code.screening.worldpayapp.offer.constraint;

import static java.util.Optional.ofNullable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class ExpirationTimeValidator implements ConstraintValidator<ExpirationTimeConstraint, Integer> {

    @Override
    public boolean isValid(Integer expirationTime, ConstraintValidatorContext context) {
        return ofNullable(expirationTime).map(time -> time > 0).orElse(false);
    }

}
