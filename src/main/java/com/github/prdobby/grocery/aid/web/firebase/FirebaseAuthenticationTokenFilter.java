package com.github.prdobby.grocery.aid.web.firebase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

public class FirebaseAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
    private static final Logger log = LoggerFactory.getLogger(FirebaseAuthenticationTokenFilter.class);

    private final static String TOKEN_HEADER = "X-Firebase-Auth";

    public FirebaseAuthenticationTokenFilter() {
        super("/recipes");
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) {
        log.trace("Attempting authentication using Firebase on {} request to {}", request.getMethod(), request.getRequestURL());
        final String authToken = request.getHeader(TOKEN_HEADER);

        if (authToken == null || authToken.isEmpty()) {
            log.debug("No auth token provided, continuing on");
            throw new PreAuthenticatedCredentialsNotFoundException("No auth token provided");
        }

        log.debug("Found authentication token, attempting authentication using Firebase");
        return getAuthenticationManager().authenticate(new FirebaseAuthenticationToken(authToken));
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        log.debug("Successful authentication using Firebase");

        chain.doFilter(request, response);
    }
}
