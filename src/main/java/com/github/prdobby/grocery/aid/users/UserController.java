package com.github.prdobby.grocery.aid.users;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserManager userService;

    public UserController(final UserManager userService) {
        this.userService = userService;
    }

    @PostMapping
    public void signUp(final Authentication authentication) {
        log.trace("Received request to create a new user");
        final String authId = Objects.toString(authentication.getPrincipal());

        this.userService.registerUser(authId);
    }
}
