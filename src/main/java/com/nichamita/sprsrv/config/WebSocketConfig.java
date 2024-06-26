package com.nichamita.sprsrv.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import com.nichamita.sprsrv.websocket.gateway.GatewayWebsocketHandler;

import reactor.util.Loggers;

@Configuration
public class WebSocketConfig {

    @Bean
    public HandlerMapping handlerMapping(GatewayWebsocketHandler gatewayWebsocketHandler) {
        Loggers.getLogger(getClass()).debug("Creating handler mapping");
        Map<String, WebSocketHandler> paths = new HashMap<>();
        paths.put("/gateway", gatewayWebsocketHandler);

        return new SimpleUrlHandlerMapping(paths, -1);
    }
    
}
