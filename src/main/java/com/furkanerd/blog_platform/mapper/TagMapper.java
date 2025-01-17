package com.furkanerd.blog_platform.mapper;

import com.furkanerd.blog_platform.model.PostStatus;
import com.furkanerd.blog_platform.model.dto.CreateTagsRequest;
import com.furkanerd.blog_platform.model.dto.TagResponse;
import com.furkanerd.blog_platform.model.entity.Post;
import com.furkanerd.blog_platform.model.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    Tag toTagEntity(CreateTagsRequest createTagsRequest);

    @Mapping(target = "postCount",source = "posts",qualifiedByName = "calculatePostCount")
    TagResponse toTagResponse(Tag tag);

    List<TagResponse> toTagResponseList(List<Tag> tags);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts){
        if(posts==null){
            return 0;
        }
        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getPostStatus()))
                .count();
    }
}
