package com.furkanerd.blog_platform.controller;

import com.furkanerd.blog_platform.mapper.TagMapper;
import com.furkanerd.blog_platform.model.dto.CreateTagsRequest;
import com.furkanerd.blog_platform.model.dto.TagResponse;
import com.furkanerd.blog_platform.model.entity.Tag;
import com.furkanerd.blog_platform.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tags")
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    public TagController(TagService tagService, TagMapper tagMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags(){
        List<Tag> tagList = tagService.getAllTags();
        return ResponseEntity.ok(tagMapper.toTagResponseList(tagList));
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(@RequestBody CreateTagsRequest createTagsRequest){
        List<Tag> savedTags = tagService.createTags(createTagsRequest.names());
        List<TagResponse> tagResponseList = tagMapper.toTagResponseList(savedTags);
        return new ResponseEntity<>(
                tagResponseList,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID tagId){
        tagService.deleteTag(tagId);
        return ResponseEntity.noContent().build();
    }
}
