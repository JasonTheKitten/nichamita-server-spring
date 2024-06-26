package com.nichamita.sprsrv.json.gateway.event;

import com.nichamita.sprsrv.json.entity.MessageEntitySerializer;
import com.nichamita.sprsrv.model.gateway.event.MessageEvent;

import dev.mccue.json.Json;

public class MessageEventSerializer {

    private MessageEventSerializer() {}

    public static Json serialize(MessageEvent messageEvent) {
        return Json.objectBuilder()
            .put("type", messageEvent.type())
            .put("message", MessageEntitySerializer.serialize(messageEvent.message()))
            .build();
    }
    
}
