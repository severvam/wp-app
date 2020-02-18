package com.code.screening.worldpayapp.offer.scheduler;

import com.code.screening.worldpayapp.offer.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OfferScheduledTask {

    private final OfferService offerService;

    @Scheduled(fixedRate = 5000)
    public void expireOffers() {
        offerService.expireOffers();
    }

}
