package com.nichamita.sprsrv.json.gateway.event;

import com.nichamita.sprsrv.json.JsonSerializer;
import com.nichamita.sprsrv.model.gateway.event.MessageEvent;

import dev.mccue.json.Json;

public class MessageEventSerializer implements JsonSerializer<MessageEvent>{

    @Override
    public Json serialize(MessageEvent messageEvent) {
        return Json.objectBuilder()
            .put("type", messageEvent.type())
            .put("message", messageEvent.message())
            .build();
    }
    
}
