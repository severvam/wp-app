package com.code.screening.worldpayapp.customer

import static org.springframework.http.HttpStatus.OK

import org.springframework.http.ResponseEntity

import com.code.screening.worldpayapp.WebIntegrationSpec
import com.code.screening.worldpayapp.customer.dto.Customer

class CustomerResourceIntegrationSpec extends WebIntegrationSpec {

    void 'should get all available customers'() {
        when:
            ResponseEntity<List<Customer>> response = restTemplate.getForEntity(apiUrl("customers"), List)
        then:
            response.statusCode == OK
            response.body
            response.body.size() == 2
    }

}
