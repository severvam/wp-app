package com.code.screening.worldpayapp.user;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.screening.worldpayapp.user.dto.User;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
public class UsersResource {

    private final UserService userService;

    @GetMapping
    public List<User> users() {
        return userService.allUsers();
    }

}
