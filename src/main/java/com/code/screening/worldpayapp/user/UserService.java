package com.code.screening.worldpayapp.user;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.code.screening.worldpayapp.user.domain.UserRepository;
import com.code.screening.worldpayapp.user.dto.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ConversionService conversionService;

    public List<User> allUsers() {
        return userRepository.findAll()
            .stream()
            .map(user -> conversionService.convert(user, User.class))
            .collect(toUnmodifiableList());
    }

}
