package com.furkanerd.blog_platform.mapper;

import com.furkanerd.blog_platform.model.dto.CreatePostRequest;
import com.furkanerd.blog_platform.model.dto.CreatePostRequestDto;
import com.furkanerd.blog_platform.model.dto.PostDto;
import com.furkanerd.blog_platform.model.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    @Mapping(target = "author",source = "author")
    @Mapping(target = "category",source = "category")
    @Mapping(target = "tags",source = "tags")
    PostDto toDto(Post post);

    List<PostDto> toDtoList(List<Post> postList);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto createPostRequestDto);
}
