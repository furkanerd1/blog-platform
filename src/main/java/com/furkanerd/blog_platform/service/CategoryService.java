package com.furkanerd.blog_platform.service;

import com.furkanerd.blog_platform.model.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> listCategories();

    Category createCategory(Category category);

    void deleteCategory(UUID categoryId);
}
