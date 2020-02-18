package com.code.screening.worldpayapp.offer.domain;

import com.code.screening.worldpayapp.currency.domain.Currency;
import com.code.screening.worldpayapp.customer.domain.Customer;
import com.code.screening.worldpayapp.user.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.code.screening.worldpayapp.offer.domain.Status.ACTIVE;
import static java.time.LocalDateTime.now;

@Entity
@Data
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String offerKey;

	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "currency_id", nullable = false)
	private Currency currency;

	@Enumerated(EnumType.STRING)
	private Status status = ACTIVE;

	private LocalDateTime createdDateTime = now();

	private LocalDateTime expiryDateTime;

	private String description;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "responsible_manager_id", nullable = false)
	private User responsibleManager;

}
