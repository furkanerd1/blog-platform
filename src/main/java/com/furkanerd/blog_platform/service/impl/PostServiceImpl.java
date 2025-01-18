package com.furkanerd.blog_platform.service.impl;

import com.furkanerd.blog_platform.model.PostStatus;
import com.furkanerd.blog_platform.model.dto.CreatePostRequest;
import com.furkanerd.blog_platform.model.dto.UpdatePostRequest;
import com.furkanerd.blog_platform.model.entity.Category;
import com.furkanerd.blog_platform.model.entity.Post;
import com.furkanerd.blog_platform.model.entity.Tag;
import com.furkanerd.blog_platform.model.entity.User;
import com.furkanerd.blog_platform.repository.PostRepository;
import com.furkanerd.blog_platform.repository.TagRepository;
import com.furkanerd.blog_platform.service.CategoryService;
import com.furkanerd.blog_platform.service.PostService;
import com.furkanerd.blog_platform.service.TagService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final TagRepository tagRepository;

    public PostServiceImpl(PostRepository postRepository, CategoryService categoryService, TagService tagService, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.tagRepository = tagRepository;
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

    @Override
    @Transactional
    public Post createPost(User user, CreatePostRequest createPostRequest) {
        Post newPost = new Post();
        newPost.setTitle(createPostRequest.title());
        newPost.setContent(createPostRequest.content());
        newPost.setPostStatus(createPostRequest.status());
        newPost.setAuthor(user);
        newPost.setReadingTime(calculateTheReadingTime(createPostRequest.content()));
        Category category = categoryService.getCategoryById(createPostRequest.categoryId());
        newPost.setCategory(category);
        Set<UUID> tagIds = createPostRequest.tagIds();
        List<Tag> tags = tagService.getTagByIds(tagIds);
        newPost.setTags(new HashSet<>(tags));
        return postRepository.save(newPost);
    }

    @Override
    @Transactional
    public Post updatePost(UUID postId, UpdatePostRequest updatePostRequest) {
        Post toUpdate = postRepository.findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("Post not found with id : " + postId));
        toUpdate.setTitle(updatePostRequest.title());
        String content = updatePostRequest.content();
        toUpdate.setContent(content);
        toUpdate.setPostStatus(updatePostRequest.status());
        toUpdate.setReadingTime(calculateTheReadingTime(content));
        UUID requestCategoryId = updatePostRequest.categoryId();
         if(!toUpdate.getCategory().getId().equals(requestCategoryId)){
             Category newCategory = categoryService.getCategoryById(requestCategoryId);
             toUpdate.setCategory(newCategory);
         }
         Set<UUID> requestTagIds = updatePostRequest.tagIds();
         Set<UUID> existingTagIds = toUpdate.getTags().stream().map(Tag::getId).collect(Collectors.toSet());
         if(!existingTagIds.equals(requestTagIds)){
             List<Tag> newTags = tagService.getTagByIds(requestTagIds);
             toUpdate.setTags(new HashSet<>(newTags));
         }
        return postRepository.save(toUpdate);
    }

    @Override
    public Post getPostById(UUID postId) {
        return postRepository.findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("Post not found with id : " + postId));
    }

    @Override
    @Transactional
    public void deletePostById(UUID postId) {
        if(!postRepository.existsById(postId))  {
            throw new EntityNotFoundException("Post not found with id : " + postId);
        }
        postRepository.deleteById(postId);
    }

    private Integer calculateTheReadingTime(String content){
        if(content==null || content.isEmpty()){
            return 0;
        }
        int wordCount=content.trim().split("\\s+").length;
        return  (int) Math.ceil((double)wordCount/200);
    }
}


