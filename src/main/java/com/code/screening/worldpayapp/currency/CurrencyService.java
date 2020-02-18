package com.code.screening.worldpayapp.currency;

import com.code.screening.worldpayapp.currency.domain.CurrencyRepository;
import com.code.screening.worldpayapp.currency.dto.Currency;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@Service
@AllArgsConstructor
public class CurrencyService {

	private final CurrencyRepository currencyRepository;
	private final ConversionService conversionService;

	List<Currency> availableCurrencies() {
		return currencyRepository.findAll()
				.stream()
				.map(currency -> conversionService.convert(currency, Currency.class))
				.collect(toUnmodifiableList());
	}

}
