package controllers;

import models.Category;
import models.Subcategory;
import org.springframework.web.bind.annotation.RestController;
import repo.SubcategoryRepository;

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
