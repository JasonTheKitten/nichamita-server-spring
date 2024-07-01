package com.nichamita.sprsrv.service.user;

import reactor.core.publisher.Mono;

public interface UserService {
    
    Mono<String> createUserAccount(String username, String email, String password);

}
