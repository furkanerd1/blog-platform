package com.furkanerd.blog_platform.model.dto;

import lombok.Builder;

@Builder
public record AuthResponse (
        String token,
        long expiresIn
){}
