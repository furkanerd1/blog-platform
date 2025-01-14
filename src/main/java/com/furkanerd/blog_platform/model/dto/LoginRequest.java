package com.furkanerd.blog_platform.model.dto;

import lombok.Builder;

@Builder
public record LoginRequest(
        String email,
        String password
){}
