package com.nichamita.sprsrv.json.rest.request;

import java.util.Map;

import com.nichamita.sprsrv.json.JsonDeserializer;
import com.nichamita.sprsrv.model.rest.request.MessageSendRequest;
import com.nichamita.sprsrv.model.rest.request.RestRequest;

import dev.mccue.json.Json;

public final class RestRequestSerializationUtil {

    private static final Map<Class<? extends RestRequest>, JsonDeserializer<? extends RestRequest>> deserializers = Map.ofEntries(
        Map.entry(MessageSendRequest.class, new MessageSendRequestSerializer())
    );
    
    private RestRequestSerializationUtil() {}

    @SuppressWarnings("unchecked")
    public static <T> T deserialize(Class<T> clazz, Json json) {
        return (T) ((JsonDeserializer<T>) deserializers.get(clazz)).deserialize(json);
    }

    public static boolean canDeserialize(Class<?> clazz) {
        return deserializers.containsKey(clazz);
    }

}
