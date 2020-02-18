package com.code.screening.worldpayapp.offer;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.code.screening.worldpayapp.offer.dto.Offer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/offers", produces = APPLICATION_JSON_VALUE)
public class OffersResource {

	private final OfferService offerService;

	@GetMapping
	public List<Offer> activeOffers() {
		return offerService.findAllActiveOffers();
	}
}
