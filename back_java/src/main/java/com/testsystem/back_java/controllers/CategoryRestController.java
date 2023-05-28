package com.testsystem.back_java.controllers;

import com.testsystem.back_java.dto.CategoryDto;
import com.testsystem.back_java.dto.SubcategoryDto;
import com.testsystem.back_java.models.Category;
import com.testsystem.back_java.services.CategoryService;
import com.testsystem.back_java.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:5173/")
public class CategoryRestController {
    private final CategoryService categoryService;
    private final SubcategoryService subcategoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService, SubcategoryService subcategoryService) {
        this.categoryService = categoryService;
        this.subcategoryService = subcategoryService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getAllCategory(){
        List<Category> categories = categoryService.findAllCategory();
        categories.forEach(c->{

        });
        return categories;
    }
    @RequestMapping(value = "/categoryDTO", method = RequestMethod.GET) //http://localhost:8080/category/categoryDTO
    public List<CategoryDto> getAllCategoryDto(){
        List<CategoryDto> categoryDtoList = categoryService.findAllCategoryDto();
        List<SubcategoryDto> subcategoryDtoList = subcategoryService.findAllSubcategoryDto();
        categoryDtoList.forEach(c->{
            List<SubcategoryDto>filterSubcategoryDtoList =
                    subcategoryDtoList.stream()
                            .filter(subcategoryDto -> subcategoryDto.getCategoryId()==c.getId())
                            .collect(Collectors.toList());
            c.setSubcategoryDtoList(filterSubcategoryDtoList);
        });
        return categoryDtoList;
    }

}
