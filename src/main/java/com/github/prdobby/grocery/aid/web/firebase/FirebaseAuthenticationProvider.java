package com.github.prdobby.grocery.aid.web.firebase;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {
    private static final Logger log = LoggerFactory.getLogger(FirebaseAuthenticationProvider.class);

    private final FirebaseAuth auth;

    public FirebaseAuthenticationProvider(final FirebaseAuth auth) {
        if (auth == null) {
            throw new NullPointerException("FirebaseAuth cannot be null");
        }
        this.auth = auth;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        try {
            final String credentials = Objects.toString(authentication.getCredentials());
            log.debug("Authenticating using credentials: {}", credentials);
            final FirebaseToken token = auth.verifyIdToken(credentials);
            return new FirebaseAuthenticationToken(credentials, token);
        } catch(final FirebaseAuthException e) {
            throw new AuthenticationServiceException("Error attempting to authenticate using Firebase", e);
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return FirebaseAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
