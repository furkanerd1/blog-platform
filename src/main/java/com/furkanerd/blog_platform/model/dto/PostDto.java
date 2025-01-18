package com.furkanerd.blog_platform.model.dto;

import com.furkanerd.blog_platform.model.PostStatus;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record PostDto(
        UUID id,
        String title,
        String content,
        AuthorDto author,
        CategoryDto category,
        Set<TagResponse> tags,
        Integer readingTime,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        PostStatus status
){}
