package com.code.screening.worldpayapp.offer;

import static com.code.screening.worldpayapp.offer.domain.specification.OfferSpecifications.activeOffers;
import static com.code.screening.worldpayapp.offer.domain.specification.OfferSpecifications.selectActiveOfferByKey;
import static com.code.screening.worldpayapp.offer.domain.specification.OfferSpecifications.selectExpiredOffers;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import java.util.UUID;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.code.screening.worldpayapp.offer.domain.OfferRepository;
import com.code.screening.worldpayapp.offer.domain.Status;
import com.code.screening.worldpayapp.offer.dto.CreateOfferRequest;
import com.code.screening.worldpayapp.offer.dto.Offer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OfferService {

	private final OfferRepository offerRepository;
	private final ConversionService conversionService;

	public List<Offer> findAllActiveOffers() {
		return offerRepository.findAll(activeOffers())
				.stream()
				.map(offer -> conversionService.convert(offer, Offer.class))
				.collect(toUnmodifiableList());
	}

	public Offer createOffer(CreateOfferRequest createOfferRequest) {
        var offer = conversionService.convert(createOfferRequest, com.code.screening.worldpayapp.offer.domain.Offer.class);
        offer.setOfferKey(UUID.randomUUID().toString());
        return conversionService.convert(offerRepository.save(offer), Offer.class);
	}

    public Offer getOffer(String offerKey) {
        return offerRepository.findOne(selectActiveOfferByKey(offerKey))
            .map(offer -> conversionService.convert(offer, Offer.class))
            .orElseThrow(OfferNotFoundException::new);
    }

    public Offer takeOffer(String offerKey) {
        return offerRepository.findOne(selectActiveOfferByKey(offerKey)).map(offer ->{
            offer.setStatus(Status.TAKEN);
            return conversionService.convert(offerRepository.save(offer), Offer.class);
        }).orElseThrow(OfferNotFoundException::new);
    }

    public Offer cancelOffer(Long id) {
        return offerRepository.findById(id).map(offer ->{
            offer.setStatus(Status.CANCELLED);
            return conversionService.convert(offerRepository.save(offer), Offer.class);
        }).orElseThrow(OfferNotFoundException::new);
    }

    public void expireOffers() {
        offerRepository.findAll(selectExpiredOffers()).forEach(offer -> {
            offer.setStatus(Status.EXPIRED);
            offerRepository.save(offer);
        });
    }

}
