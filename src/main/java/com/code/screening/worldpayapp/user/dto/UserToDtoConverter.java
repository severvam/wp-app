package com.code.screening.worldpayapp.user.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.code.screening.worldpayapp.user.domain.User;

@Component
public class UserToDtoConverter implements Converter<User, com.code.screening.worldpayapp.user.dto.User> {

	@Override
	public com.code.screening.worldpayapp.user.dto.User convert(User source) {
        return new com.code.screening.worldpayapp.user.dto.User(source.getId(), source.getDisplayName());
	}

}
