package com.furkanerd.blog_platform.repository;

import com.furkanerd.blog_platform.model.PostStatus;
import com.furkanerd.blog_platform.model.entity.Category;
import com.furkanerd.blog_platform.model.entity.Post;
import com.furkanerd.blog_platform.model.entity.Tag;
import com.furkanerd.blog_platform.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByPostStatusAndCategoryAndTagsContaining(PostStatus postStatus, Category category, Tag tag);
    List<Post> findAllByPostStatusAndCategory(PostStatus postStatus, Category category);
    List<Post> findAllByPostStatusAndTagsContaining(PostStatus postStatus, Tag tag);
    List<Post> findAllByPostStatus(PostStatus postStatus);
    List<Post> findAllByPostStatusAndAuthor(PostStatus postStatus, User user);

}
