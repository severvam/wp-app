package com.code.screening.worldpayapp.offer.dto

import com.code.screening.worldpayapp.currency.domain.Currency
import com.code.screening.worldpayapp.customer.domain.Customer
import com.code.screening.worldpayapp.offer.domain.Status
import com.code.screening.worldpayapp.user.domain.User
import spock.lang.Specification

import java.time.LocalDateTime

class OfferToDtoConverterSpec extends Specification {

    OfferToDtoConverter converter = new OfferToDtoConverter()

    void 'should convert from offer domain object to dto'() {
        given:
            def offer = createOffer()
        when:
            def offerDto = converter.convert(offer)
        then:
            offerDto.id == offer.id
            offerDto.description == offer.description
            offerDto.status == offer.status
            offerDto.responsibleManager == offer.responsibleManager.displayName
            offerDto.customer.id == offer.customer.id
            offerDto.customer.name == offer.customer.name
            offerDto.customer.surname == offer.customer.surname
            offerDto.currency == offer.currency.code
            offerDto.price == offer.price
            offerDto.createdDateTime == offer.createdDateTime
            offerDto.expiryDateTime == offer.expiryDateTime
    }

    com.code.screening.worldpayapp.offer.domain.Offer createOffer() {
        def offer = new com.code.screening.worldpayapp.offer.domain.Offer()
        def now = LocalDateTime.now()
        offer.id = 1l
        offer.createdDateTime = now
        offer.offerKey = UUID.randomUUID().toString()
        offer.expiryDateTime = now.plusMinutes(10)
        offer.price = 11.23
        offer.currency = new Currency(id: 1l, code: 'EUR')
        offer.customer = new Customer(id: 1l, name: 'Aaa', surname: 'Bbbb')
        offer.responsibleManager = new User(id: 1l, displayName: 'super manager')
        offer.status = Status.ACTIVE
        offer.description = 'small description'
        offer
    }

}
