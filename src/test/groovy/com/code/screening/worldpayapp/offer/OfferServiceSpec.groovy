package com.code.screening.worldpayapp.offer

import com.code.screening.worldpayapp.offer.domain.Offer
import com.code.screening.worldpayapp.offer.domain.OfferRepository
import com.code.screening.worldpayapp.offer.dto.CreateOfferRequest
import org.springframework.core.convert.ConversionService
import spock.lang.Specification
import spock.lang.Subject

class OfferServiceSpec extends Specification {

    OfferRepository offerRepository = Mock()
    ConversionService conversionService = Mock()

    @Subject
    OfferService offerService = new OfferService(offerRepository, conversionService)

    void 'should find active offers'() {
        setup:
            def offer = new Offer()
            def offerDto = com.code.screening.worldpayapp.offer.dto.Offer.builder().build()
        when:
            def offers = offerService.findAllActiveOffers()
        then:
            1 * offerRepository.findAll(_) >> [offer]
            1 * conversionService.convert(offer, com.code.screening.worldpayapp.offer.dto.Offer) >> offerDto
            offers
            offers.size() == 1
            offers[0] == offerDto
    }

    void 'should create offers'() {
        setup:
            def offerRequest = new CreateOfferRequest()
            def offer = new Offer()
            def offerDto = com.code.screening.worldpayapp.offer.dto.Offer.builder().build()
        when:
            def savedOffer = offerService.createOffer(offerRequest)
        then:
            1 * conversionService.convert(offerRequest, Offer) >> offer
            1 * offerRepository.save(offer) >> offer
            1 * conversionService.convert(offer, com.code.screening.worldpayapp.offer.dto.Offer) >> offerDto
            savedOffer == offerDto
    }

    void 'should read active offer by offerKey'() {
        setup:
            String offerKey = 'wewr'
            def offer = new Offer()
            def offerDto = com.code.screening.worldpayapp.offer.dto.Offer.builder().build()
        when:
            def foundOffer = offerService.getOffer(offerKey)
        then:
            1 * offerRepository.findOne(_) >> Optional.of(offer)
            1 * conversionService.convert(offer, com.code.screening.worldpayapp.offer.dto.Offer) >> offerDto
            foundOffer == offerDto
    }

    void 'should fail, no offers found'() {
        setup:
            String offerKey = 'wewr'
        when:
            offerService.getOffer(offerKey)
        then:
            1 * offerRepository.findOne(_) >> Optional.ofNullable(null)
            thrown OfferNotFoundException
    }

    void 'should take offer'() {
        setup:
            String offerKey = 'wewr'
            def offer = new Offer()
            def offerDto = com.code.screening.worldpayapp.offer.dto.Offer.builder().build()
        when:
            def takenOffer = offerService.takeOffer(offerKey)
        then:
            1 * offerRepository.findOne(_) >> Optional.of(offer)
            1 * offerRepository.save(offer) >> offer
            1 * conversionService.convert(offer, com.code.screening.worldpayapp.offer.dto.Offer) >> offerDto
            takenOffer == offerDto
    }

    void 'should fail, no offers found for taking'() {
        setup:
            String offerKey = 'wewr'
        when:
            offerService.takeOffer(offerKey)
        then:
            1 * offerRepository.findOne(_) >> Optional.ofNullable(null)
            thrown OfferNotFoundException
    }

    void 'should cancel offer'() {
        setup:
            Long id = 1l
            def offer = new Offer()
            def offerDto = com.code.screening.worldpayapp.offer.dto.Offer.builder().build()
        when:
            def takenOffer = offerService.cancelOffer(id)
        then:
            1 * offerRepository.findById(id) >> Optional.of(offer)
            1 * offerRepository.save(offer) >> offer
            1 * conversionService.convert(offer, com.code.screening.worldpayapp.offer.dto.Offer) >> offerDto
            takenOffer == offerDto
    }

    void 'should fail, no offers found for cancelling'() {
        setup:
            Long id = 1l
        when:
            offerService.cancelOffer(id)
        then:
            1 * offerRepository.findById(id) >> Optional.ofNullable(null)
            thrown OfferNotFoundException
    }

    void 'should expire offers'() {
        setup:
            def offer1 = new Offer()
            def offer2 = new Offer()
        when:
            offerService.expireOffers()
        then:
            1 * offerRepository.findAll(_) >> [offer1, offer2]
            2 * offerRepository.save(_ as Offer)
    }

}
