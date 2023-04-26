package com.yomeDev.blogapp.services.serviesimpls;

import com.yomeDev.blogapp.models.Category;
import com.yomeDev.blogapp.payloads.CategoryDto;
import com.yomeDev.blogapp.repositories.CategoryRepo;
import com.yomeDev.blogapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepository;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .name(categoryDto.getName())
                .build();
        Category savedCategory = categoryRepository.save(category);
        CategoryDto categoryResponse = new CategoryDto();
        categoryResponse.setName(savedCategory.getName());
        return categoryResponse;
    }
    @Override
    public Category viewCategory(Long id){
       Category category=categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
        return category;
    }


    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id){
        Category category=categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Not Found"));
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return categoryDto;
    }
}