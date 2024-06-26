package com.nichamita.sprsrv.websocket.gateway.imp;

import java.time.Duration;

import org.springframework.web.reactive.socket.WebSocketSession;

import com.nichamita.sprsrv.json.gateway.event.GatewayEventSerializationUtil;
import com.nichamita.sprsrv.model.gateway.event.GatewayEvent;
import com.nichamita.sprsrv.service.gateway.GatewaySession;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.util.Loggers;

public class GatewaySessionImp implements GatewaySession {

    private final WebSocketSession session;

    private FluxSink<GatewayEvent> sink;

    public GatewaySessionImp(WebSocketSession session) {
        this.session = session;
    }

    @Override
    public Mono<Void> emit(GatewayEvent event) {
        return Mono.defer(() -> {
            if (sink != null) {
                sink.next(event);
                return Mono.empty();
            }

            return Mono.error(new IllegalStateException("Session not started"));
        });
    }

    public Mono<Void> start() {
        return session.send(Flux.<GatewayEvent>create(sink -> {
            this.sink = sink;
        }).map(event -> {
            String json = GatewayEventSerializationUtil.serialize(event).toString();
            Loggers.getLogger(getClass()).info("Emitting event: " + json);
            return  session.textMessage(json);
        }))
        .doFinally(signalType -> {
            Loggers.getLogger(getClass()).debug("Session closed: " + session.getId());
            sink.complete();
        });
    }
    
}
