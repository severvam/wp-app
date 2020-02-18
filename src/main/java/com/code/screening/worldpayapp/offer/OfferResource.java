package com.code.screening.worldpayapp.offer;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import com.code.screening.worldpayapp.offer.dto.CreateOfferRequest;
import com.code.screening.worldpayapp.offer.dto.Offer;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/offer", produces = APPLICATION_JSON_VALUE)
public class OfferResource {

	private final OfferService offerService;

	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	public Offer createOffer(@Valid @RequestBody CreateOfferRequest offer) {
		return offerService.createOffer(offer);
	}

	@GetMapping("/{offerKey}")
	public Offer getOffer(@PathVariable String offerKey) {
        return offerService.getOffer(offerKey);
    }

    @PatchMapping("/{offerKey}")
    public Offer takeOffer(@PathVariable String offerKey) {
        return offerService.takeOffer(offerKey);
    }

    @PatchMapping("/cancel/{id}")
    public Offer cancelOffer(@PathVariable Long id) {
        return offerService.cancelOffer(id);
    }

}
