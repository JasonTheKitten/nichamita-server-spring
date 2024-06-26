package com.nichamita.sprsrv.json.gateway.event;

import java.util.Map;

import com.nichamita.sprsrv.json.JsonSerializer;
import com.nichamita.sprsrv.model.gateway.event.GatewayEvent;
import com.nichamita.sprsrv.model.gateway.event.MessageEvent;

import dev.mccue.json.Json;

public final class GatewayEventSerializationUtil {
    
    private static final Map<String, JsonSerializer<? extends GatewayEvent>> serializers = Map.ofEntries(
        Map.entry("message", (JsonSerializer<MessageEvent>) MessageEventSerializer::serialize)
    );

    @SuppressWarnings("unchecked")
    public static <T extends GatewayEvent> Json serialize(T event) {
        return ((JsonSerializer<T>) serializers.get(event.type())).serialize(event);
    }

}
