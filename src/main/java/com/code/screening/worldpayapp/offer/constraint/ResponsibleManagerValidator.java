package com.code.screening.worldpayapp.offer.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.code.screening.worldpayapp.user.domain.UserRepository;

import lombok.AllArgsConstructor;

import static java.util.Optional.ofNullable;

@AllArgsConstructor
class ResponsibleManagerValidator implements ConstraintValidator<ResponsibleManagerConstraint, Long> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return ofNullable(id)
                .map(userId -> userRepository.findById(id).isPresent())
                .orElse(true);
    }

}
