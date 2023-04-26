package com.yomeDev.blogapp.controllers;

import com.yomeDev.blogapp.payloads.CategoryDto;
import com.yomeDev.blogapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    final CategoryService categoryService;

    @PostMapping("/create-category")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }
}
