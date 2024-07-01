package com.nichamita.sprsrv.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import com.nichamita.sprsrv.model.misc.Snowflake;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class AppAuthenticationToken extends AbstractAuthenticationToken {

    private final Jws<Claims> jwt;
    private final Snowflake principal;

    public AppAuthenticationToken(Jws<Claims> jwt) {
        super(null);
        this.setDetails(jwt);
        this.jwt = jwt;
        this.principal = Snowflake.fromString(jwt.getPayload().get("sub", String.class));
    }

    @Override
    public Jws<Claims> getCredentials() {
        return jwt;
    }

    @Override
    public Snowflake getPrincipal() {
        return principal;
    }
    
}
