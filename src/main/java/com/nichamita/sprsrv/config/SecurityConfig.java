package com.nichamita.sprsrv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig  {

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {
        http.authorizeExchange(exchanges -> exchanges
            .anyExchange().permitAll());
        http.cors(spec -> spec.configurationSource(request -> {
            var cors = new CorsConfiguration();
            cors.addAllowedOrigin("*");
            cors.addAllowedMethod("*");
            cors.addAllowedHeader("*");
            return cors;
        }));
        return http.build();
    }

}
