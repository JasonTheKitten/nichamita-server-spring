package com.nichamita.sprsrv.websocket.gateway;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.nichamita.sprsrv.service.gateway.GatewayService;
import com.nichamita.sprsrv.websocket.gateway.imp.GatewaySessionImp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@Component
public class GatewayWebsocketHandler implements WebSocketHandler {

    private static final Duration KEEP_ALIVE = Duration.ofSeconds(10);

    private static final Logger log = Loggers.getLogger(GatewayWebsocketHandler.class);

    private final GatewayService gatewayService;

    @Autowired
    public GatewayWebsocketHandler(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return Mono.defer(() -> {
            log.debug("New session: " + session.getId());
            GatewaySessionImp gatewaySession = new GatewaySessionImp(session);
            gatewayService.registerSession(gatewaySession);
            // Check every 10 seconds if the session is still open
            return gatewaySession.start()
                .thenMany(Flux.interval(KEEP_ALIVE))
                .map(i -> session.isOpen())
                .takeUntil(open -> !open)
                .doFinally(signalType -> {
                    log.debug("Session closed: " + session.getId());
                    gatewayService.unregisterSession(new GatewaySessionImp(session));
                })
                .then();
        });
    }
    
}
