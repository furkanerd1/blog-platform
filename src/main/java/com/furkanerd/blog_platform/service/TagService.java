package com.furkanerd.blog_platform.service;

import com.furkanerd.blog_platform.model.entity.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {

    List<Tag> getAllTags();

    List<Tag> createTags(Set<String> tagNames);

    void deleteTag(UUID tagId);

    Tag getTagById(UUID tagId);
}
