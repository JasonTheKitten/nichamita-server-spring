package com.nichamita.sprsrv.security.jwt;

import java.util.Date;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.nichamita.sprsrv.security.AppAuthenticationToken;

import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@Component
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private static final Logger log = Loggers.getLogger(JwtAuthenticationManager.class);
 
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
            .cast(AppAuthenticationToken.class)
            .doOnNext(jwtToken -> log.debug("Checking authentication"))
            .filter(jwtToken -> jwtToken.getCredentials().getPayload().getExpiration().compareTo(new Date()) >= 0)
            .switchIfEmpty(Mono.error(new IllegalArgumentException("Token is expired")))
            .doOnNext(jwtToken -> jwtToken.setAuthenticated(true))
            .map(jwtToken -> jwtToken);
    }

}
