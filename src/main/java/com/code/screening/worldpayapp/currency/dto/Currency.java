package com.code.screening.worldpayapp.currency.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Currency {

	private final Long id;
	private final String code;
	private final String description;

}
