package com.testsystem.back_java.controllers;

import com.testsystem.back_java.dto.CategoryDto;
import com.testsystem.back_java.dto.SubcategoryDto;
import com.testsystem.back_java.models.Category;
import com.testsystem.back_java.models.Subcategory;
import com.testsystem.back_java.services.impl.CategoryServiceImpl;
import com.testsystem.back_java.services.impl.SubcategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:5173/")
public class CategoryRestController {
    private final CategoryServiceImpl categoryService;
    private final SubcategoryServiceImpl subcategoryServiceImpl;

    @Autowired
    public CategoryRestController(CategoryServiceImpl categoryServiceImpl, SubcategoryServiceImpl subcategoryServiceImpl) {
        this.categoryService = categoryServiceImpl;
        this.subcategoryServiceImpl = subcategoryServiceImpl;
    }

    /*List<CategoryDto>*/
    @RequestMapping(value = "/categoryDTO", method = RequestMethod.GET) //http://localhost:8080/category/categoryDTO
    public ResponseEntity<List<CategoryDto>> getAllCategoryDto(){
        List<CategoryDto> categoryDtoList = categoryService.findAllCategoryDto();
        List<SubcategoryDto> subcategoryDtoList = subcategoryServiceImpl.findAllSubcategoryDto();
        categoryDtoList.forEach(c->{
            List<SubcategoryDto>filterSubcategoryDtoList =
                    subcategoryDtoList.stream()
                            .filter(subcategoryDto -> subcategoryDto.getCategoryId()==c.getId())
                            .collect(Collectors.toList());
            c.setSubcategoryDtoList(filterSubcategoryDtoList);
        });
        return ResponseEntity.ok(categoryDtoList);
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Secured({"Admin_Role", "Teacher_Role"})
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        Category newCategory = categoryService.convertCategoryDtoInEntity(categoryDto);
        Category saveCategory =categoryService.saveCategory(newCategory);

        return ResponseEntity.ok(categoryService.convertEntityInCategoryDto(saveCategory));
    }
    @RequestMapping(value = "/createSub", method = RequestMethod.POST)
    @Secured({"Admin_Role", "Teacher_Role"})
    public ResponseEntity<SubcategoryDto> createCategory(@RequestBody SubcategoryDto subcategoryDto) {
        Category newCategory = categoryService.getCategory(subcategoryDto.getCategoryId());
        Subcategory subcategory = subcategoryServiceImpl.convertSubcategoryToEntity(subcategoryDto);
        subcategory.setCategory(newCategory);
        Subcategory saveSubcategory = subcategoryServiceImpl.saveSubcategory(subcategory);

        return ResponseEntity.ok(subcategoryServiceImpl.convertEntityToDto(saveSubcategory));
    }

}
