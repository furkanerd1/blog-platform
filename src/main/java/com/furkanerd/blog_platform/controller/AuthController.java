package com.furkanerd.blog_platform.controller;

import com.furkanerd.blog_platform.model.dto.AuthResponse;
import com.furkanerd.blog_platform.model.dto.LoginRequest;
import com.furkanerd.blog_platform.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        UserDetails user = authenticationService.authenticate(
                loginRequest.email(),
                loginRequest.password()
        );
        AuthResponse authResponse =AuthResponse.builder()
                .token(authenticationService.generateToken(user))
                .expiresIn(86400) //24 hours in seconds
                .build();
        return ResponseEntity.ok(authResponse);
    }
}
