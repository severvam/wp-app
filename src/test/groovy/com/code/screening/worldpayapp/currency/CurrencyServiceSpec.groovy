package com.code.screening.worldpayapp.currency

import com.code.screening.worldpayapp.currency.dto.Currency
import org.springframework.core.convert.ConversionService

import com.code.screening.worldpayapp.currency.domain.CurrencyRepository

import spock.lang.Specification
import spock.lang.Subject

class CurrencyServiceSpec extends Specification {

    CurrencyRepository currencyRepository = Mock()
    ConversionService conversionService = Mock()

    @Subject
    CurrencyService currencyService = new CurrencyService(currencyRepository, conversionService)

    void 'should return converted currency list'() {
        given:
            def currency =  new com.code.screening.worldpayapp.currency.domain.Currency()
            def currencyDto = Currency.builder().build()
        when:
            List<Currency> currencies = currencyService.availableCurrencies()
        then:
            1 * currencyRepository.findAll() >> [currency]
            1 * conversionService.convert(currency, Currency) >> currencyDto
            currencies
            currencies.size() == 1
            currencies[0] == currencyDto

    }

}
