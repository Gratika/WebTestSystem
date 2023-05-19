package com.testsystem.back_java.controllers;

import com.testsystem.back_java.models.Category;
import com.testsystem.back_java.repo.CategoryRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    private CategoryRepository categoryRepository;
    public List<Category> getAll(){
        List<Category>categories = new ArrayList<>();
        this.categoryRepository.findAll().forEach(categories::add);
        return categories;
    }
}
