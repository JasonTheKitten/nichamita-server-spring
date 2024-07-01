package com.nichamita.sprsrv.security.jwt;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;

import com.nichamita.sprsrv.security.AppAuthenticationToken;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

// TODO: Should this be made into a component?
public class JwtAuthenticationConverter implements ServerAuthenticationConverter {

    private static final Logger log = Loggers.getLogger(JwtAuthenticationConverter.class);

    private final JwtParser jwtParser;

    public JwtAuthenticationConverter(SecretKey secretKey) {
        this.jwtParser = Jwts.parser()
            .decryptWith(secretKey)
            .verifyWith(secretKey)
            .build();
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        log.trace("Converting JWT token");
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("Authorization"))
            .filter(authHeader -> authHeader.startsWith("Bearer "))
            .map(authHeader -> authHeader.substring(7))
            .map(tokenText -> jwtParser.parse(tokenText).accept(Jws.CLAIMS))
            .map(jwt -> new AppAuthenticationToken(jwt));
    }
    
}
