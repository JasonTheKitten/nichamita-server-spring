package com.nichamita.sprsrv.json.rest.request;

import com.nichamita.sprsrv.model.rest.request.LoginRequest;

import dev.mccue.json.Json;
import dev.mccue.json.JsonDecoder;

public final class LoginRequestSerializer {
    
    private LoginRequestSerializer() {}

    public static LoginRequest deserialize(Json json) {
        return new LoginRequest(
            JsonDecoder.field(json, "username", JsonDecoder::string),
            JsonDecoder.field(json, "password", JsonDecoder::string)
        );
    }

}
