package com.github.prdobby.grocery.aid.users;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findFirstByAuthId(final String authId);
}
