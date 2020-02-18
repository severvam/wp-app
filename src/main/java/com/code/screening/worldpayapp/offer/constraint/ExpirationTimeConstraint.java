package com.code.screening.worldpayapp.offer.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ExpirationTimeValidator.class)
@Target( { ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpirationTimeConstraint {

    String message() default "expiration_time_must_be_greater_than_zero_or_not_present";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
