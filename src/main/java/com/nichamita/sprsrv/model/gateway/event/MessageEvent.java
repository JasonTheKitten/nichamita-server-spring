package com.nichamita.sprsrv.model.gateway.event;

import com.nichamita.sprsrv.model.entity.Message;

public record MessageEvent(Message message) implements GatewayEvent {
 
    @Override
    public String type() {
        return "message";
    }

}
