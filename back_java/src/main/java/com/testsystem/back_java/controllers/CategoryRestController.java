package com.testsystem.back_java.controllers;

import com.testsystem.back_java.models.Category;
import com.testsystem.back_java.repo.CategoryRepository;
import com.testsystem.back_java.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/category")
public class CategoryRestController {
    private CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getAllCategory(){
        List<Category> categories = categoryService.findAllCategory();
        categories.forEach(c->{

        });
        return categories;
    }
}
