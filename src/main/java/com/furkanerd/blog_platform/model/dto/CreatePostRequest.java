package com.furkanerd.blog_platform.model.dto;

import com.furkanerd.blog_platform.model.PostStatus;
import lombok.Builder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
public record CreatePostRequest(
         String title,
         String content,
         UUID categoryId,
         Set<UUID> tagIds,
         PostStatus status
){
    public CreatePostRequest {
        if (tagIds == null) {
            tagIds = new HashSet<>();
        }
    }
}
