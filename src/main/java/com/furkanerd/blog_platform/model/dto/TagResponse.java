package com.furkanerd.blog_platform.model.dto;

import java.util.UUID;

public record TagResponse(
        UUID id,
        String name,
        Long postCount
){}
