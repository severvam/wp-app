package com.code.screening.worldpayapp.offer.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.code.screening.worldpayapp.offer.constraint.CurrencyConstraint;
import com.code.screening.worldpayapp.offer.constraint.CustomerConstraint;
import com.code.screening.worldpayapp.offer.constraint.ResponsibleManagerConstraint;

import lombok.Data;

@Data
public class CreateOfferRequest {

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 7, fraction = 2)
    private BigDecimal price;

    @Length(min = 3, max = 3)
    @CurrencyConstraint
    private String currency;

    private Integer expiryTime;

    private String description;

    @CustomerConstraint
    private Long customer;

    @ResponsibleManagerConstraint
    private Long responsibleManager;

}
