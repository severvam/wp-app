package com.code.screening.worldpayapp.currency

import static org.springframework.http.HttpStatus.OK

import org.springframework.http.ResponseEntity

import com.code.screening.worldpayapp.WebIntegrationSpec

class CurrenciesResourceIntegrationSpec extends WebIntegrationSpec {

    void 'should get all available currencies'() {
        when:
            ResponseEntity<List<Currency>> response = restTemplate.getForEntity(apiUrl("currencies"), List)
        then:
            response.statusCode == OK
            response.body
            response.body.size() == 2
    }

}
