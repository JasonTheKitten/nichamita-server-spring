package com.nichamita.sprsrv.model.rest.request;

public record LoginRequest(String username, String password) implements RestRequest {
    
}
