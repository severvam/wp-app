package com.code.screening.worldpayapp.user

import static org.springframework.http.HttpStatus.OK

import org.springframework.http.ResponseEntity

import com.code.screening.worldpayapp.WebIntegrationSpec
import com.code.screening.worldpayapp.user.dto.User

class UsersResourceSpec extends WebIntegrationSpec {

    void 'should get all available internal users'() {
        when:
            ResponseEntity<List<User>> response = restTemplate.getForEntity(apiUrl("users"), List)
        then:
            response.statusCode == OK
            response.body
            response.body.size() == 2
    }

}
