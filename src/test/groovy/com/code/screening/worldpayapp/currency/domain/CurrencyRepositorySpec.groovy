package com.code.screening.worldpayapp.currency.domain

import com.code.screening.worldpayapp.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Unroll

class CurrencyRepositorySpec extends IntegrationSpec {

    @Autowired
    CurrencyRepository currencyRepository

    @Unroll
    void 'should select #code currency by code'() {
        when:
            Currency currency = currencyRepository.findByCode(code).get()
        then:
            currency.id
            currency.code == code
        where:
            code << ['EUR', 'USD']
    }

}
