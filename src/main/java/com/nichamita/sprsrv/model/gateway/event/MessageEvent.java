package com.nichamita.sprsrv.model.gateway.event;

import com.nichamita.sprsrv.model.entity.MessageEntity;

public record MessageEvent(MessageEntity message) implements GatewayEvent {
 
    @Override
    public String type() {
        return "message";
    }

}
