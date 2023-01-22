package com.blog.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogapp.entities.Category;
import com.blog.blogapp.exceptions.ResourceNotFoundException;
import com.blog.blogapp.payloads.CategoryDto;
import com.blog.blogapp.repositories.CategoryRepo;
import com.blog.blogapp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category createdCategory = this.categoryRepo.save(this.DtoToCategory(categoryDto));
        return this.CategoryToDto(createdCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(
            "Category"," id ",categoryId
        ));

        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryTitle(categoryDto.getCategoryTitle());

        return this.CategoryToDto(this.categoryRepo.save(category));
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(
            "Category"," id ",categoryId
        ));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(
            "Category"," id ",categoryId
        ));
        return this.CategoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
         List<Category> allCategory = this.categoryRepo.findAll();
         List<CategoryDto> categoryDtos = allCategory.stream().
         map((category) -> this.CategoryToDto(category)).
         collect(Collectors.toList());
         return categoryDtos;
    }

    public CategoryDto CategoryToDto(Category category){
        return this.modelMapper.map(category, CategoryDto.class);
    }

    public Category DtoToCategory(CategoryDto categoryDto){
        return this.modelMapper.map(categoryDto, Category.class);
    }
    
}
