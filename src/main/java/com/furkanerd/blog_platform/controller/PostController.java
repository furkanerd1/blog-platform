package com.furkanerd.blog_platform.controller;

import com.furkanerd.blog_platform.mapper.PostMapper;
import com.furkanerd.blog_platform.model.dto.PostDto;
import com.furkanerd.blog_platform.model.entity.Post;
import com.furkanerd.blog_platform.model.entity.User;
import com.furkanerd.blog_platform.service.PostService;
import com.furkanerd.blog_platform.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final PostMapper postMapper;

    public PostController(PostService postService, UserService userService, PostMapper postMapper) {
        this.postService = postService;
        this.userService = userService;
        this.postMapper = postMapper;
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam(required = false) UUID categoryId,
                                                     @RequestParam(required = false) UUID tagId) {
        List<Post> postList= postService.getAllPosts(categoryId, tagId);
        return ResponseEntity.ok(postMapper.toDtoList(postList));
    }

    @GetMapping("/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userId) {
        User loggedInuser = userService.getUserById(userId);
        List<Post> postList = postService.getDraftPosts(loggedInuser);
        return ResponseEntity.ok(postMapper.toDtoList(postList));
    }
}