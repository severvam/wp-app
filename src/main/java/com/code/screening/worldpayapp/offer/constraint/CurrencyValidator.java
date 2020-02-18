package com.code.screening.worldpayapp.offer.constraint;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.code.screening.worldpayapp.currency.domain.CurrencyRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class CurrencyValidator implements ConstraintValidator<CurrencyConstraint, String> {

    private final CurrencyRepository currencyRepository;

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        if (!isEmpty(code) && code.toUpperCase().equals(code)) {
            return currencyRepository.findByCode(code).isPresent();
        }
        return false;
    }

}
