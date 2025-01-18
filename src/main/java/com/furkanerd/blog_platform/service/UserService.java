package com.furkanerd.blog_platform.service;

import com.furkanerd.blog_platform.model.entity.User;

import java.util.UUID;

public interface UserService {

    User getUserById(UUID id);
}
