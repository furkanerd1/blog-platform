package com.furkanerd.blog_platform.exception;

public record FieldError(
        String field,
        String message
){}
