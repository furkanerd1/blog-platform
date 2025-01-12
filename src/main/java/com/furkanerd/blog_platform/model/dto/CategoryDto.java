package com.furkanerd.blog_platform.model.dto;

import java.util.UUID;

public record CategoryDto(
        UUID id,
        String name,
        Long postCount
){}
