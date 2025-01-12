package com.furkanerd.blog_platform.controller;

import com.furkanerd.blog_platform.mapper.CategoryMapper;
import com.furkanerd.blog_platform.model.dto.CategoryDto;
import com.furkanerd.blog_platform.model.dto.CreateCategoryRequest;
import com.furkanerd.blog_platform.model.entity.Category;
import com.furkanerd.blog_platform.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    ResponseEntity<List<CategoryDto>> listCategories(){
        List<CategoryDto> categoryList = categoryService.listCategories()
                .stream().map(categoryMapper::toDto)
                .toList();
        return ResponseEntity.ok(categoryList);
    }

    @PostMapping
    ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
          Category categoryToCreate= categoryMapper.toEntity(createCategoryRequest);
          Category savedCategory = categoryService.createCategory(categoryToCreate);
          return  new ResponseEntity<>(
                  categoryMapper.toDto(savedCategory),
                  HttpStatus.CREATED
          );
    }
}
