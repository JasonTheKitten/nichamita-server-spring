package com.nichamita.sprsrv.service.gateway;

import com.nichamita.sprsrv.model.gateway.event.GatewayEvent;

import reactor.core.publisher.Mono;

public interface GatewaySession {
    
    Mono<Void> emit(GatewayEvent event);

}
