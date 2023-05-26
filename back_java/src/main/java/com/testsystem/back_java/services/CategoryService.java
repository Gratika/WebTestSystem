package com.testsystem.back_java.services;

import com.testsystem.back_java.models.Category;
import com.testsystem.back_java.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
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
    public void saveCategory(Category category){
        categoryRepository.save(category);
    }

    public void updateCategory(Category category){
        categoryRepository.save(category);
    }
    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }
}
