package com.nichamita.sprsrv.config;

import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.nichamita.sprsrv.security.jwt.JwtAuthenticationConverter;
import com.nichamita.sprsrv.security.jwt.JwtAuthenticationManager;
import com.nimbusds.jose.util.Base64;

import io.jsonwebtoken.security.Keys;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig  {

    @Value("${application.cors.special-origins}")
    private String[] allowedOrigins;

    @Value("${application.jwt.secret}")
    private String jwtSecret;

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(new JwtAuthenticationManager());
        authenticationWebFilter.setServerAuthenticationConverter(new JwtAuthenticationConverter(jwtSecretKey()));

        http.csrf(customizer -> customizer.disable());
        http.authorizeExchange(exchanges -> exchanges
            .pathMatchers("/user/login").permitAll()
            .pathMatchers("/user/register").permitAll()
            .pathMatchers("/gateway").permitAll()
            .anyExchange().authenticated());
        http.cors(spec -> spec.configurationSource(createCorsConfiguration()));
        http.addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHORIZATION);
        
        return http.build();
    }

    @Bean
    public SecretKey jwtSecretKey() {
        return Keys.hmacShaKeyFor(Base64.from(jwtSecret).decode());
    }

    private CorsConfigurationSource createCorsConfiguration() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration defaultCorsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", defaultCorsConfiguration);

        CorsConfiguration loginCorsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        loginCorsConfiguration.setAllowedOrigins(List.of(allowedOrigins));
        source.registerCorsConfiguration("/user/login", loginCorsConfiguration);

        return source;
    }

}
