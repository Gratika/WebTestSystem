package com.testsystem.back_java.controllers;

import com.testsystem.back_java.models.Category;
import com.testsystem.back_java.repo.CategoryRepository;
import com.testsystem.back_java.repo.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicTestController {
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public PublicTestController(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }
    @RequestMapping( "/hello" )
    public String echo() {
        return "Hello World!";
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category>categories = new ArrayList<>();
        this.categoryRepository.findAll().forEach(categories::add);
        return ResponseEntity.ok(categories);
    }

    /*@GetMapping("/subcategory")
    public List<Subcategory> getAllSubcategory(){
        List<Subcategory>subcategories = new ArrayList<>();
        this.subcategoryRepository.findAll().forEach(subcategories::add);
        return subcategories;
    }*/
}
