package com.code.screening.worldpayapp.offer.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.code.screening.worldpayapp.currency.domain.CurrencyRepository;
import com.code.screening.worldpayapp.customer.domain.CustomerRepository;
import com.code.screening.worldpayapp.offer.domain.Offer;
import com.code.screening.worldpayapp.user.domain.UserRepository;

@Component
public class DtoToOfferConverter implements Converter<CreateOfferRequest, Offer> {

    private final Integer expirationTime;

	private final CurrencyRepository currencyRepository;
	private final CustomerRepository customerRepository;
	private final UserRepository userRepository;

    public DtoToOfferConverter(@Value("${offer.expirationTime}") Integer expirationTime,
                               CurrencyRepository currencyRepository,
                               CustomerRepository customerRepository,
                               UserRepository userRepository) {
        this.expirationTime = expirationTime;
        this.currencyRepository = currencyRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @Override
	public Offer convert(CreateOfferRequest source) {
		final var offer = new Offer();
        final var now = LocalDateTime.now();
        final var expiryTime = Optional.ofNullable(source.getExpiryTime()).orElse(expirationTime);

		offer.setPrice(source.getPrice());
        offer.setCreatedDateTime(now);
        offer.setExpiryDateTime(now.plusMinutes(expiryTime));
        offer.setDescription(source.getDescription());

        currencyRepository.findByCode(source.getCurrency()).ifPresent(offer::setCurrency);
        customerRepository.findById(source.getCustomer()).ifPresent(offer::setCustomer);
        userRepository.findById(source.getResponsibleManager()).ifPresent(offer::setResponsibleManager);

		return offer;
	}

}
