package com.nichamita.sprsrv.model.gateway.event;

public record MessageEvent(String message) implements GatewayEvent {
 
    @Override
    public String type() {
        return "message";
    }

}
