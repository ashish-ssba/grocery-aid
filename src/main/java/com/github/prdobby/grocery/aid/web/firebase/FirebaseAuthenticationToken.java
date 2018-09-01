package com.github.prdobby.grocery.aid.web.firebase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.google.firebase.auth.FirebaseToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class FirebaseAuthenticationToken implements Authentication {
    private final FirebaseToken userRecord;

    private final String token;

    private boolean authenticated;

    public FirebaseAuthenticationToken(final String token) {
        this(token, null);
    }

    public FirebaseAuthenticationToken(final String token, final FirebaseToken userRecord) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token is not allowed to be null or empty");
        }
        this.token = token;
        this.userRecord = userRecord;
        this.authenticated = this.userRecord != null;
    }

    @Override
    public String getName() {
        return this.userRecord.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getDetails() {
        return Optional.ofNullable(this.userRecord);
    }

    @Override
    public Object getPrincipal() {
        if (this.isAuthenticated()) {
            return Optional.of(this.userRecord.getUid());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(final boolean isAuthenticated) {
        if (isAuthenticated) {
            throw new IllegalArgumentException("This authentication implementation is immutable");
        }

        this.authenticated = isAuthenticated;
    }
}
