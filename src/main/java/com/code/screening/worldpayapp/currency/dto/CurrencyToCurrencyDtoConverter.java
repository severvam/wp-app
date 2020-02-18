package com.code.screening.worldpayapp.currency.dto;

import com.code.screening.worldpayapp.currency.domain.Currency;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyToCurrencyDtoConverter implements Converter<Currency, com.code.screening.worldpayapp.currency.dto.Currency> {

	@Override
	public com.code.screening.worldpayapp.currency.dto.Currency convert(Currency source) {
        var currencyDisplayName = java.util.Currency.getInstance(source.getCode()).getDisplayName();
        return new com.code.screening.worldpayapp.currency.dto.Currency(source.getId(), source.getCode(), currencyDisplayName);
	}

}
