package com.testsystem.back_java.controllers;

import com.testsystem.back_java.dto.SubcategoryDto;
import com.testsystem.back_java.models.Subcategory;
import com.testsystem.back_java.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.testsystem.back_java.repo.SubcategoryRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subcategory")
public class SubcategoryRestController {
    private SubcategoryService subcategoryService;
    @Autowired
    public SubcategoryRestController(SubcategoryService subcategoryService){
        this.subcategoryService = subcategoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Subcategory> getAllSubcategory(){
        return subcategoryService.findAllSubcategory();
    }
    @RequestMapping(value = "/subcstegoryDto", method = RequestMethod.GET)
    public List<SubcategoryDto> getAllSubcategoryDto(){
        return subcategoryService.findAllSubcategoryDto();
    }
}
