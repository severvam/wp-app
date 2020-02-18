package com.code.screening.worldpayapp.offer.dto;

import com.code.screening.worldpayapp.customer.dto.Customer;
import com.code.screening.worldpayapp.offer.domain.Status;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class Offer {

	private final Long id;
	private final String offerKey;
	private final BigDecimal price;
	private final String currency;
	private final Status status;
	private final LocalDateTime createdDateTime;
	private final LocalDateTime expiryDateTime;
	private final String description;
	private final Customer customer;
	private final String responsibleManager;

}
