package com.github.prdobby.grocery.aid.users;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

@Service
public class UserManager {
    private static final Logger log = LoggerFactory.getLogger(UserManager.class);

    private final UserRepository repository;

    public UserManager(final UserRepository userRepository) {
        this.repository = userRepository;
    }

    public GroceryAidUser registerUser(final String authId) {
        final User savedUser = this.repository.save(new User(null, authId, null));

        log.debug("Created new user at {}", savedUser.getCreatedDate());
        return new GroceryAidUser(savedUser.getId());
    }

    public GroceryAidUser getUser(final String authId) {
        log.debug("Attempting to retrieve user");

        final Optional<User> user = this.repository.findFirstByAuthId(authId);
        if (user.isPresent()) {
            return new GroceryAidUser(user.get().getId());
        } else {
            log.warn("Could not find a user with the given id: {}", authId);
            throw new IllegalArgumentException("Could not find a user with that id");
        }
    }
}