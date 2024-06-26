package com.nichamita.sprsrv.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Mono;
import reactor.util.Loggers;

@Component
public class GatewayWebsocketHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Loggers.getLogger(getClass()).info("New session: " + session.getId());
        return session.send(Mono.just(session.textMessage("{\"type\":\"message\", \"message\":\"Hello!\"}")));
    }
    
}
