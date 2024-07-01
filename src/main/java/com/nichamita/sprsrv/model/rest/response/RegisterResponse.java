package com.nichamita.sprsrv.model.rest.response;

public record RegisterResponse(String token, String password) implements RestResponse {
    
}
