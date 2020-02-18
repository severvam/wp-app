package com.code.screening.worldpayapp.currency.dto

import spock.lang.Specification
import spock.lang.Unroll

class CurrencyToCurrencyDtoConverterSpec extends Specification {

    CurrencyToCurrencyDtoConverter converter = new CurrencyToCurrencyDtoConverter()

    @Unroll
    void 'should convert from #expectedCode domain currency to dto'() {
        when:
            Currency currencyDto = converter.convert(currency)
        then:
            currencyDto.code == expectedCode
            currencyDto.description == expectedDescription
        where:
            currency                || expectedCode || expectedDescription
            createCurrency('EUR')   || 'EUR'        || 'Euro'
            createCurrency('USD')   || 'USD'        || 'US Dollar'
            createCurrency('CAD')   || 'CAD'        || 'Canadian Dollar'
    }

    void 'should fail if currency is unknown'() {
        when:
            converter.convert(createCurrency('ABC'))
        then:
            thrown IllegalArgumentException
    }

    com.code.screening.worldpayapp.currency.domain.Currency createCurrency(String code) {
        new com.code.screening.worldpayapp.currency.domain.Currency(id: 1, code: code)
    }

}
