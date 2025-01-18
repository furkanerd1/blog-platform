package com.furkanerd.blog_platform.model.dto;
import com.furkanerd.blog_platform.model.PostStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public record UpdatePostRequest(
        UUID id,
        String title,
        String content,
        UUID categoryId,
        Set<UUID> tagIds,
        PostStatus status
){
    public UpdatePostRequest{
        if(tagIds==null){
            tagIds = new HashSet<>();
        }
    }
}
