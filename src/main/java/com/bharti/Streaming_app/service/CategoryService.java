package com.bharti.Streaming_app.service;

import com.bharti.Streaming_app.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
    void deleteCategory(int categoryId);
    CategoryDto getCategory(int categoryId);
    List<CategoryDto> getAllCategory();
}
