package com.yomeDev.blogapp.services;

import com.yomeDev.blogapp.models.Category;
import com.yomeDev.blogapp.payloads.CategoryDto;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);

    Category viewCategory(Long id);

    CategoryDto updateCategory(CategoryDto categoryDto, Long id);
}
