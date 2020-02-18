package com.code.screening.worldpayapp.currency;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.code.screening.worldpayapp.currency.dto.Currency;

@RestController
@RequestMapping(value = "/currencies", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CurrenciesResource {

	private final CurrencyService currencyService;

	@GetMapping
	public List<Currency> availableCurrencies() {
		return currencyService.availableCurrencies();
	}

}
