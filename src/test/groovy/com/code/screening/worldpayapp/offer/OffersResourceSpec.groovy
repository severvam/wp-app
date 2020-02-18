package com.code.screening.worldpayapp.offer

import static org.springframework.http.HttpStatus.OK

import org.springframework.http.ResponseEntity

import com.code.screening.worldpayapp.WebIntegrationSpec
import com.code.screening.worldpayapp.offer.dto.Offer

class OffersResourceSpec extends WebIntegrationSpec {

    void 'should get all available offers'() {
        when:
            ResponseEntity<List<Offer>> response = restTemplate.getForEntity(apiUrl("offers"), List)
        then:
            response.statusCode == OK
            !response.body
    }

}
