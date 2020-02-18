package com.code.screening.worldpayapp.offer.dto;

import com.code.screening.worldpayapp.customer.dto.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OfferToDtoConverter implements Converter<com.code.screening.worldpayapp.offer.domain.Offer, Offer> {

	@Override
	public Offer convert(com.code.screening.worldpayapp.offer.domain.Offer source) {
		return Offer.builder()
				.id(source.getId())
                .offerKey(source.getOfferKey())
				.price(source.getPrice())
				.currency(source.getCurrency().getCode())
				.status(source.getStatus())
				.createdDateTime(source.getCreatedDateTime())
				.expiryDateTime(source.getExpiryDateTime())
				.description(source.getDescription())
				.customer(convertCustomer(source.getCustomer()))
				.responsibleManager(source.getResponsibleManager().getDisplayName())
				.build();
	}

	private Customer convertCustomer(com.code.screening.worldpayapp.customer.domain.Customer customer) {
		return Customer.builder()
				.id(customer.getId())
				.name(customer.getName())
				.surname(customer.getSurname())
				.build();
	}

}
