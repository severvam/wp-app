package com.code.screening.worldpayapp.offer.dto

import com.code.screening.worldpayapp.currency.domain.CurrencyRepository
import com.code.screening.worldpayapp.customer.domain.Customer
import com.code.screening.worldpayapp.customer.domain.CustomerRepository
import com.code.screening.worldpayapp.user.domain.User
import com.code.screening.worldpayapp.user.domain.UserRepository

import com.code.screening.worldpayapp.currency.domain.Currency

import spock.lang.Specification
import spock.lang.Subject

import static java.util.Optional.of

class DtoToOfferConverterSpec extends Specification {

    Integer expirationTime = 10

    CurrencyRepository currencyRepository = Mock()
    CustomerRepository customerRepository = Mock()
    UserRepository userRepository = Mock()

    @Subject
    DtoToOfferConverter converter = new DtoToOfferConverter(expirationTime, currencyRepository, customerRepository, userRepository)

    void 'should convert from dto to domain object'() {
        given:
            def offerRequest = new CreateOfferRequest()
            offerRequest.price = 11.23
            offerRequest.currency = 'EUR'
            offerRequest.customer = 1l
            offerRequest.responsibleManager = 1l
            offerRequest.description = 'desc'
        when:
            def offer = converter.convert(offerRequest)
        then:
            1 * currencyRepository.findByCode(offerRequest.currency) >> of(new Currency(code: offerRequest.currency))
            1 * customerRepository.findById(offerRequest.customer) >> of(new Customer(id: offerRequest.customer))
            1 * userRepository.findById(offerRequest.responsibleManager) >> of(new User(id: offerRequest.responsibleManager))

            offer.price == offerRequest.price
            offer.currency.code == offerRequest.currency
            offer.customer.id == offerRequest.customer
            offer.responsibleManager.id == offerRequest.responsibleManager
            offer.description == offerRequest.description
            offer.expiryDateTime == offer.createdDateTime.plusMinutes(expirationTime)
    }

}
