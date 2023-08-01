package com.testsystem.back_java.services;

import com.testsystem.back_java.dto.CategoryDto;
import com.testsystem.back_java.models.Category;

import java.util.List;

public interface ICategoryService {
    public List<Category> findAllCategory();
    public Category getCategory (long id);
    public Category saveCategory(Category category);
    public void updateCategory(Category category);
    public void deleteCategory(long id);
    public List<CategoryDto> findAllCategoryDto();
    CategoryDto convertEntityInCategoryDto(Category category);
    Category convertCategoryDtoInEntity(CategoryDto categoryDto);
}
