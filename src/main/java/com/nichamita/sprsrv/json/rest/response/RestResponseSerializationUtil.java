package com.nichamita.sprsrv.json.rest.response;

import java.util.Map;

import com.nichamita.sprsrv.json.JsonSerializer;
import com.nichamita.sprsrv.model.rest.response.LoginResponse;
import com.nichamita.sprsrv.model.rest.response.RestResponse;

import dev.mccue.json.Json;

public final class RestResponseSerializationUtil {

    private static final Map<Class<? extends RestResponse>, JsonSerializer<? extends RestResponse>> SERIALIZERS = Map.ofEntries(
        Map.entry(LoginResponse.class, (JsonSerializer<LoginResponse>) LoginResponseSerializer::serialize)
    );

    private RestResponseSerializationUtil() {}

    @SuppressWarnings("unchecked")
    public static <T extends RestResponse> Json serialize(Class<? extends T> clazz, T restResponse) {
        return ((JsonSerializer<T>) SERIALIZERS.get(clazz)).serialize(restResponse);
    }

    public static boolean canSerialize(Class<?> clazz) {
        return SERIALIZERS.containsKey(clazz);
    }

}
