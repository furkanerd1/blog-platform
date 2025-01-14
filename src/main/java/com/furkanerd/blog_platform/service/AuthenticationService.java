package com.furkanerd.blog_platform.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    UserDetails authenticate(String email , String password);
    String generateToken(UserDetails userDetails);
    UserDetails validToken(String token);
}
