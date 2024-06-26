package com.nichamita.sprsrv.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import com.nichamita.sprsrv.config.codec.RestRequestDecoder;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {
    
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.customCodecs().register(new RestRequestDecoder());
	}

}
