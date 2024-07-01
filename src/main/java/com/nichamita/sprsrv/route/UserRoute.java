package com.nichamita.sprsrv.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nichamita.sprsrv.model.rest.request.LoginRequest;
import com.nichamita.sprsrv.model.rest.request.RegisterRequest;
import com.nichamita.sprsrv.model.rest.response.LoginResponse;
import com.nichamita.sprsrv.model.rest.response.RegisterResponse;
import com.nichamita.sprsrv.service.user.UserService;

import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@RestController
@RequestMapping("/user")
public class UserRoute {

    private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890$!*";

    private static final Logger log = Loggers.getLogger(UserRoute.class);

    private final UserService userService;

    @Autowired
    public UserRoute(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/login")
    public Mono<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return Mono.just(loginRequest)
            .doOnNext(request -> log.info("Login request: {}", request))
            .then(Mono.just(new LoginResponse("token")));
    }

    @PostMapping("/register")
    public Mono<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        return Mono.just(registerRequest)
            .doOnNext(request -> log.info("Register request: {}", request))
            .then(Mono.just(generatePassword()))
            .flatMap(password -> userService
                .createUserAccount(registerRequest.username(), registerRequest.email(), password)
                .map(token -> new RegisterResponse(token, password)));
    }

    private String generatePassword() {
        int passwordLength = (int) (Math.random() * 6) + 12;
        char[] password = new char[passwordLength];
        for (int i = 0; i < passwordLength; i++) {
            password[i] = ALLOWED_CHARS.charAt(
                (int) (Math.random() * ALLOWED_CHARS.length()));
        }

        return new String(password);
    }

}
