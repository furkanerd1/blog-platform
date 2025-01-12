package com.furkanerd.blog_platform.mapper;


import com.furkanerd.blog_platform.model.PostStatus;
import com.furkanerd.blog_platform.model.dto.CategoryDto;
import com.furkanerd.blog_platform.model.dto.CreateCategoryRequest;
import com.furkanerd.blog_platform.model.entity.Category;
import com.furkanerd.blog_platform.model.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts){
        if(posts == null){
            return 0;
        }
        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getPostStatus()))
                .count();
    }

}
