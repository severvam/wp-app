package com.code.screening.worldpayapp.offer.domain

import com.code.screening.worldpayapp.IntegrationSpec
import com.code.screening.worldpayapp.currency.domain.CurrencyRepository
import com.code.screening.worldpayapp.customer.domain.CustomerRepository
import com.code.screening.worldpayapp.user.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

import java.time.LocalDateTime

import static com.code.screening.worldpayapp.offer.domain.specification.OfferSpecifications.activeOffers
import static com.code.screening.worldpayapp.offer.domain.specification.OfferSpecifications.selectActiveOfferByKey
import static com.code.screening.worldpayapp.offer.domain.specification.OfferSpecifications.selectExpiredOffers

class OfferRepositorySpec extends IntegrationSpec {

    @Subject
    @Autowired
    OfferRepository offerRepository

    @Autowired
    UserRepository userRepository
    @Autowired
    CustomerRepository customerRepository
    @Autowired
    CurrencyRepository currencyRepository

    void 'should select active offers'() {
        setup:
            cleanOffers()
            def offer = new Offer()
            offer.offerKey = UUID.randomUUID().toString()
            offer.responsibleManager = userRepository.findById(1l).get()
            offer.customer = customerRepository.findById(1l).get()
            offer.currency = currencyRepository.findById(1l).get()
            offer.price = 23.43
            offer.expiryDateTime = LocalDateTime.now().plusMinutes(10)

            def savedOffer = offerRepository.save(offer)
        when:
            List<Offer> offers = offerRepository.findAll(activeOffers())
        then:
            offers
            offers.size() == 1
            offers[0] == savedOffer
    }

    void 'should select active offer by offerKey'() {
        setup:
            cleanOffers()
            def offer = new Offer()
            offer.offerKey = UUID.randomUUID().toString()
            offer.responsibleManager = userRepository.findById(1l).get()
            offer.customer = customerRepository.findById(1l).get()
            offer.currency = currencyRepository.findById(1l).get()
            offer.price = 23.43
            offer.expiryDateTime = LocalDateTime.now().plusMinutes(10)

            def savedOffer = offerRepository.save(offer)
        when:
            Offer activeOffer = offerRepository.findOne(selectActiveOfferByKey(savedOffer.offerKey)).get()
        then:
            activeOffer == savedOffer
    }

    void 'should select expired offers'() {
        setup:
            cleanOffers()
            def now = LocalDateTime.now()
            def offer = new Offer()
            offer.offerKey = UUID.randomUUID().toString()
            offer.responsibleManager = userRepository.findById(1l).get()
            offer.customer = customerRepository.findById(1l).get()
            offer.currency = currencyRepository.findById(1l).get()
            offer.price = 23.43
            offer.createdDateTime = now.minusDays(11)
            offer.expiryDateTime = now.minusDays(10)

            def savedOffer = offerRepository.save(offer)
        when:
            List<Offer> offers = offerRepository.findAll(selectExpiredOffers())
        then:
            offers
            offers.size() == 1
            offers[0] == savedOffer
    }

    void cleanOffers() {
        offerRepository.findAll().forEach { offerRepository.delete(it) }
    }

}
