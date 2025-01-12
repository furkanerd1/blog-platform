package com.furkanerd.blog_platform.service.impl;

import com.furkanerd.blog_platform.model.entity.Category;
import com.furkanerd.blog_platform.repository.CategoryRepository;
import com.furkanerd.blog_platform.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }
}
