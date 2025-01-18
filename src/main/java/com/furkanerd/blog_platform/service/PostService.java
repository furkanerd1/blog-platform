package com.furkanerd.blog_platform.service;

import com.furkanerd.blog_platform.model.dto.CreatePostRequest;
import com.furkanerd.blog_platform.model.entity.Post;
import com.furkanerd.blog_platform.model.entity.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId, UUID tagId);

    List<Post> getDraftPosts(User user);

    Post createPost(User user , CreatePostRequest createPostRequest);
}
