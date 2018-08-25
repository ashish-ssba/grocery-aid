package com.github.prdobby.grocery.aid.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import com.github.prdobby.grocery.aid.web.firebase.FirebaseAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    List<AuthenticationProvider> providers;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(providers);
    }

    public GenericFilterBean authenticationTokenFilterBean() throws Exception {
        FirebaseAuthenticationTokenFilter authTokenFilter = new FirebaseAuthenticationTokenFilter();
        authTokenFilter.setAuthenticationManager(authenticationManager());
        authTokenFilter.setAuthenticationSuccessHandler((req, res, auth) -> {});
        return authTokenFilter;
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("**/favicon.ico").permitAll()
                .antMatchers("/csv/**").permitAll()
                .antMatchers("/**").authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}
