package com.furkanerd.blog_platform.service.impl;

import com.furkanerd.blog_platform.model.PostStatus;
import com.furkanerd.blog_platform.model.entity.Category;
import com.furkanerd.blog_platform.model.entity.Post;
import com.furkanerd.blog_platform.model.entity.Tag;
import com.furkanerd.blog_platform.model.entity.User;
import com.furkanerd.blog_platform.repository.PostRepository;
import com.furkanerd.blog_platform.service.CategoryService;
import com.furkanerd.blog_platform.service.PostService;
import com.furkanerd.blog_platform.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    public PostServiceImpl(PostRepository postRepository, CategoryService categoryService, TagService tagService) {
        this.postRepository = postRepository;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
        if(categoryId != null && tagId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByPostStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED,
                    category,
                    tag
            );
        }
        if(categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByPostStatusAndCategory(
                    PostStatus.PUBLISHED,
                    category
            );
        }
        if (tagId != null) {
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByPostStatusAndTagsContaining(
                    PostStatus.PUBLISHED,
                    tag
            );
        }
        return postRepository.findAllByPostStatus(
                PostStatus.PUBLISHED
        );
    }

    @Override
    public List<Post> getDraftPosts(User user) {
        return postRepository.findAllByPostStatusAndAuthor(
                PostStatus.DRAFT,
                user
        );
    }
}


