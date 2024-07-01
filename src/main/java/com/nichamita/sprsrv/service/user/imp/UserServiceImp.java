package com.nichamita.sprsrv.service.user.imp;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nichamita.sprsrv.model.misc.Snowflake;
import com.nichamita.sprsrv.service.snowflake.SnowflakeService;
import com.nichamita.sprsrv.service.user.UserService;

import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImp implements UserService {

    @Value("${application.jwt.expiration}")
    private long expiration;

    private final SnowflakeService snowflakeService;
    private final SecretKey jwtSecretKey;

    @Autowired
    public UserServiceImp(SnowflakeService snowflakeService, SecretKey jwtSecretKey) {
        this.snowflakeService = snowflakeService;
        this.jwtSecretKey = jwtSecretKey;
    }

    @Override
    public Mono<String> createUserAccount(String username, String email, String password) {
        Snowflake snowflake = snowflakeService.generateId();

        return Mono.just(Jwts.builder()
            .subject(snowflake.stringValue())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expiration * 1000))
            .signWith(jwtSecretKey)
            .compact());
    }
    
}
