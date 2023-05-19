package com.testsystem.back_java.controllers;

import com.testsystem.back_java.models.Subcategory;
import org.springframework.web.bind.annotation.RestController;
import com.testsystem.back_java.repo.SubcategoryRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SubcategoryController {
    private SubcategoryRepository subcategoryRepository;

    public List<Subcategory> getAll(){
        List<Subcategory>subcategories = new ArrayList<>();
        this.subcategoryRepository.findAll().forEach(subcategories::add);
        return subcategories;
    }
}
