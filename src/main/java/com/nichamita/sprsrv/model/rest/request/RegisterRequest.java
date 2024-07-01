package com.nichamita.sprsrv.model.rest.request;

public record RegisterRequest(String username, String email) implements RestRequest {
    
}
