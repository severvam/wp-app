package com.code.screening.worldpayapp.offer

import com.code.screening.worldpayapp.WebIntegrationSpec
import com.code.screening.worldpayapp.offer.domain.Status
import com.code.screening.worldpayapp.offer.dto.CreateOfferRequest
import com.code.screening.worldpayapp.offer.dto.Offer
import org.springframework.http.ResponseEntity
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.OK

class OfferResourceSpec extends WebIntegrationSpec {

    static final String OFFER_PATH = "offer"

    void setup() {
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory())
    }

    void 'should save new offer'() {
        setup:
            def offerRequest = createOfferRequest(11.22, 'EUR', 1l, 'some description', 5, 1l)
        when:
            ResponseEntity<Offer> response = restTemplate.postForEntity(apiUrl(OFFER_PATH), offerRequest, Offer)
        then:
            response.statusCode == OK
            response.body
    }

    void 'should fail because no price'() {
        setup:
            def offerRequest = createOfferRequest(null, 'EUR', 1l, 'some description', 5, 1l)
        when:
            ResponseEntity response = restTemplate.postForEntity(apiUrl(OFFER_PATH), offerRequest, null)
        then:
            response.statusCode == BAD_REQUEST
            !response.body
    }

    void 'should fail because no currency'() {
        setup:
            def offerRequest = createOfferRequest(123.4, null, 1l, 'some description', 5, 1l)
        when:
            ResponseEntity response = restTemplate.postForEntity(apiUrl(OFFER_PATH), offerRequest, null)
        then:
            response.statusCode == BAD_REQUEST
            !response.body
    }

    void 'should fail because currency is not supported'() {
        setup:
            def offerRequest = createOfferRequest(123.4, 'PLN', 1l, 'some description', 5, 1l)
        when:
            ResponseEntity response = restTemplate.postForEntity(apiUrl(OFFER_PATH), offerRequest, null)
        then:
            response.statusCode == BAD_REQUEST
            !response.body
    }

    void 'should fail because no customer'() {
        setup:
            def offerRequest = createOfferRequest(123.4, 'EUR', null, 'some description', 5, 1l)
        when:
            ResponseEntity response = restTemplate.postForEntity(apiUrl(OFFER_PATH), offerRequest, null)
        then:
            response.statusCode == BAD_REQUEST
            !response.body
    }

    void 'should fail because no responsibleManager'() {
        setup:
            def offerRequest = createOfferRequest(123.4, 'EUR', 1l, 'some description', 5, null)
        when:
            ResponseEntity response = restTemplate.postForEntity(apiUrl(OFFER_PATH), offerRequest, null)
        then:
            response.statusCode == BAD_REQUEST
            !response.body
    }

    void 'should load offer by offerKey'() {
        setup:
            def offerRequest = createOfferRequest(11.22, 'EUR', 1l, 'some description', 5, 1l)
            ResponseEntity<Offer> postResponse = restTemplate.postForEntity(apiUrl(OFFER_PATH), offerRequest, Offer)
        when:
            ResponseEntity<Offer> getResponse = restTemplate.getForEntity(apiUrl(OFFER_PATH + '/' + postResponse.body.offerKey), Offer)
        then:
            getResponse.statusCode == OK
            getResponse.body == postResponse.body
    }

    void 'should take offer by offerKey'() {
        setup:
            def offerRequest = createOfferRequest(11.22, 'EUR', 1l, 'some description', 5, 1l)
            ResponseEntity<Offer> postResponse = restTemplate.postForEntity(apiUrl(OFFER_PATH), offerRequest, Offer)
        when:
            Offer offer = restTemplate.patchForObject(apiUrl(OFFER_PATH + '/' + postResponse.body.offerKey), null, Offer)
        then:
            offer.status == Status.TAKEN
    }

    void 'should cancel offer by id, because cancel is available only for responsible person'() {
        setup:
            def offerRequest = createOfferRequest(11.22, 'EUR', 1l, 'some description', 5, 1l)
            ResponseEntity<Offer> postResponse = restTemplate.postForEntity(apiUrl(OFFER_PATH), offerRequest, Offer)
        when:
            Offer offer = restTemplate.patchForObject(apiUrl(OFFER_PATH + '/cancel/' + postResponse.body.id), null, Offer)
        then:
            offer.status == Status.CANCELLED
    }

    CreateOfferRequest createOfferRequest(BigDecimal price, String currency, Long customer, String description, Integer expiryTime, Long responsibleManager) {
        def offerRequest = new CreateOfferRequest()
        offerRequest.price = price
        offerRequest.currency = currency
        offerRequest.customer = customer
        offerRequest.description = description
        offerRequest.expiryTime = expiryTime
        offerRequest.responsibleManager = responsibleManager
        offerRequest
    }

}
