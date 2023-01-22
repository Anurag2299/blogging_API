package com.blog.blogapp.services;

import java.util.List;

import com.blog.blogapp.payloads.CategoryDto;

public interface CategoryService {
    
    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    //delete
    void deleteCategory(Integer categoryId);

    //get

    CategoryDto getCategory(Integer categoryId);

    List<CategoryDto> getAllCategory();
}
