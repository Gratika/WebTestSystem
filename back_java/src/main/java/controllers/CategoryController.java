package controllers;

import models.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import repo.CategoryRepository;

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
