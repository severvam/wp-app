package com.code.screening.worldpayapp.offer.domain.specification;

import com.code.screening.worldpayapp.offer.domain.Offer;
import com.code.screening.worldpayapp.offer.domain.Offer_;
import com.code.screening.worldpayapp.offer.domain.Status;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OfferSpecifications {

	public static Specification<Offer> activeOffers() {
		return (Specification<Offer>) (root, query, criteriaBuilder) -> criteriaBuilder.and(
				criteriaBuilder.equal(root.get(Offer_.status), Status.ACTIVE),
				criteriaBuilder.greaterThanOrEqualTo(root.get(Offer_.expiryDateTime), LocalDateTime.now())
		);
	}

    public static Specification<Offer> selectActiveOfferByKey(String offerKey) {
        return (Specification<Offer>) (root, query, criteriaBuilder) -> criteriaBuilder.and(
            criteriaBuilder.equal(root.get(Offer_.status), Status.ACTIVE),
            criteriaBuilder.greaterThanOrEqualTo(root.get(Offer_.expiryDateTime), LocalDateTime.now()),
            criteriaBuilder.equal(root.get(Offer_.offerKey), offerKey)
        );
    }

    public static Specification<Offer> selectExpiredOffers() {
        return (Specification<Offer>) (root, query, criteriaBuilder) -> criteriaBuilder.and(
            criteriaBuilder.equal(root.get(Offer_.status), Status.ACTIVE),
            criteriaBuilder.lessThan(root.get(Offer_.expiryDateTime), LocalDateTime.now())
        );
    }


}
