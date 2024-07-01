package com.nichamita.sprsrv.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nichamita.sprsrv.model.entity.Message;
import com.nichamita.sprsrv.model.gateway.event.MessageEvent;
import com.nichamita.sprsrv.model.rest.request.MessageSendRequest;
import com.nichamita.sprsrv.service.gateway.GatewayService;
import com.nichamita.sprsrv.service.snowflake.SnowflakeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@RestController
@RequestMapping("/message")
public class MessageRoute {

    private static final Logger log = Loggers.getLogger(MessageRoute.class);

    private final GatewayService gatewayService;
    private final SnowflakeService snowflakeService;

    @Autowired
    public MessageRoute(GatewayService gatewayService, SnowflakeService snowflakeService) {
        this.gatewayService = gatewayService;
        this.snowflakeService = snowflakeService;
    }
    
    @PostMapping
    public Mono<Void> postMessage(@RequestBody MessageSendRequest messageSendRequest) {
        log.debug("Received message: " + messageSendRequest.message());
        String message = messageSendRequest.message().trim();
        if (message.isEmpty()) {
            return Mono.error(new IllegalArgumentException("Message cannot be empty"));
        }
        MessageEvent messageEvent = new MessageEvent(new Message(
            snowflakeService.generateId().longValue(),
            message,
            System.currentTimeMillis(),
            Math.random() > 0.5 ? 1 : 2));
        return Flux.fromIterable(gatewayService.sessions().collect())
            .flatMap(session -> session.emit(messageEvent))
            .then();
    }

}
