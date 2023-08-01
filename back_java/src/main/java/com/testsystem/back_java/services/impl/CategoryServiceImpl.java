package com.testsystem.back_java.services.impl;

import com.testsystem.back_java.dto.CategoryDto;
import com.testsystem.back_java.models.Category;
import com.testsystem.back_java.models.Subcategory;
import com.testsystem.back_java.repo.CategoryRepository;
import com.testsystem.back_java.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category>findAllCategory(){
        List <Category> categories=new ArrayList();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }
    public Category getCategory (long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()) {return optionalCategory.get();}
        return null;
    }
    public Category saveCategory(Category category){
        Category saveCategory = categoryRepository.save(category);
        return saveCategory;
    }

    public void updateCategory(Category category){
        categoryRepository.save(category);
    }
    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }

    public List<CategoryDto> findAllCategoryDto(){
        return this.findAllCategory()
                .stream()
                .map(this::convertEntityInCategoryDto)
                .collect(Collectors.toList());

    }

    @Override
    public CategoryDto convertEntityInCategoryDto(Category category){
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setSubcategoryDtoList(new ArrayList<>());
        return categoryDto;
    }
    public Category convertCategoryDtoInEntity(CategoryDto categoryDto){
        Category newCategory = new Category();
        newCategory.setName(categoryDto.getName());
        return newCategory;
    }
}
