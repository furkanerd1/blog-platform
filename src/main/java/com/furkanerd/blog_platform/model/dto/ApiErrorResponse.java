package com.furkanerd.blog_platform.model.dto;

import com.furkanerd.blog_platform.exception.FieldError;
import lombok.Builder;

import java.util.List;

@Builder
public record ApiErrorResponse(
        int status,
        String message,
        List<FieldError> errors
){}


