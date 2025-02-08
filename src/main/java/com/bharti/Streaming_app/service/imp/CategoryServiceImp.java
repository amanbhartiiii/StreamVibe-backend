package com.bharti.Streaming_app.service.imp;

import com.bharti.Streaming_app.payload.ApiResponse;
import com.bharti.Streaming_app.payload.CategoryDto;
import com.bharti.Streaming_app.entity.Category;
import com.bharti.Streaming_app.repo.CategoryRepo;
import com.bharti.Streaming_app.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new RuntimeException("Category not found with id: "+categoryId));
        if(categoryDto.getCategoryTitle() != null) {
            category.setCategoryTitle(categoryDto.getCategoryTitle());
        }
        if(categoryDto.getCategoryDescription() != null) {
            category.setCategoryDescription(categoryDto.getCategoryDescription());
        }

        Category updatedCategory = categoryRepo.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new RuntimeException("Category not found with id: "+categoryId));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new RuntimeException("Category not found with id: "+categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos =  categories.stream().map((category -> modelMapper.map(category, CategoryDto.class))).toList();
        return categoryDtos;
    }
}
