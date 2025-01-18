package com.furkanerd.blog_platform.service.impl;

import com.furkanerd.blog_platform.model.entity.User;
import com.furkanerd.blog_platform.repository.UserRepository;
import com.furkanerd.blog_platform.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id : " + id));
    }
}
