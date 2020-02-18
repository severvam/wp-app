package com.code.screening.worldpayapp

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort

import spock.lang.Specification

@SpringBootTest(webEnvironment = RANDOM_PORT)
class WebIntegrationSpec extends Specification {

    @LocalServerPort
    int serverPort

    @Autowired
    TestRestTemplate restTemplate

    URI apiUrl(String uri) {
        return "http://localhost:$serverPort/$uri".toURI()
    }

}
