package com.code.screening.worldpayapp.offer.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

@Documented
@Constraint(validatedBy = ResponsibleManagerValidator.class)
@Target( { ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotNull
public @interface ResponsibleManagerConstraint {

    String message() default "responsible_manager_not_found";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
