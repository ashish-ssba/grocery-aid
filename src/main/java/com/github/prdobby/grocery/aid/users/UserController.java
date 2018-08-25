package com.github.prdobby.grocery.aid.users;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/")
public class UserController {

    private final UserRepository repository;

    public UserController(final UserRepository userRepository) {
        this.repository = userRepository;
    }

    @PostMapping
    public void signUp() {
    }
}
