package com.code.screening.worldpayapp.offer.scheduler

import com.code.screening.worldpayapp.offer.OfferService
import spock.lang.Specification
import spock.lang.Subject

class OfferScheduledTaskSpec extends Specification {

    OfferService offerService = Mock()

    @Subject
    OfferScheduledTask task = new OfferScheduledTask(offerService)

    void 'should verify service is used'() {
        when:
            task.expireOffers()
        then:
            1 * offerService.expireOffers()
    }

}
