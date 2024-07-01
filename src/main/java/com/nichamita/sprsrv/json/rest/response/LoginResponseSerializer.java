package com.nichamita.sprsrv.json.rest.response;

import com.nichamita.sprsrv.model.rest.response.LoginResponse;

import dev.mccue.json.Json;

public final class LoginResponseSerializer {
    
    private LoginResponseSerializer() {}

    public static Json serialize(LoginResponse loginResponse) {
        return Json.objectBuilder()
            .put("token", loginResponse.token())
            .build();
    }

}
