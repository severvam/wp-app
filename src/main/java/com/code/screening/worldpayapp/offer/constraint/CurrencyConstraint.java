package com.code.screening.worldpayapp.offer.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;

@Documented
@Constraint(validatedBy = CurrencyValidator.class)
@Target( { ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotEmpty
public @interface CurrencyConstraint {

    String message() default "currency_is_not_supported";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
