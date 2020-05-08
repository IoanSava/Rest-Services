package com.rest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * This class configures the services
 * to be used by an authenticated user.
 */
@Configuration
@EnableWebSecurity
public class JWTConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JWTAuthentication(authenticationManager()))
                .antMatchers("/players").hasRole("ADMIN")
                .antMatchers("/games").hasRole("ADMIN")
                .antMarchers("/player_games").hasRole("ADMIN");
    }
}
