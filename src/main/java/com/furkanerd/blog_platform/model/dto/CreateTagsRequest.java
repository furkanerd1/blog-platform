package com.furkanerd.blog_platform.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record CreateTagsRequest(
        @NotEmpty(message = "At least one tag name required")
        @Size(max = 10,message = "Maximum {max} tag allowed")
        Set<@Size(min = 2, max = 30, message = "Tag name must be between {min} and {max} characters")
        @Pattern(regexp = "^[\\w\\s-]+$", message = "Tag name can only contain letters, numbers, spaces, and hyphens")
                String> names
){}
