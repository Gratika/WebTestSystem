package controllers;

import models.Category;
import models.Subcategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repo.CategoryRepository;
import repo.SubcategoryRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicTestController {
    private CategoryRepository categoryRepository;
    private SubcategoryRepository subcategoryRepository;
    @GetMapping("/categories")
    public List<Category> getAllCategory(){
        List<Category>categories = new ArrayList<>();
        this.categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    @GetMapping("/subcategory")
    public List<Subcategory> getAllSubcategory(){
        List<Subcategory>subcategories = new ArrayList<>();
        this.subcategoryRepository.findAll().forEach(subcategories::add);
        return subcategories;
    }
}
