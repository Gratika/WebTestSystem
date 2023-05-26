package com.testsystem.back_java.services;

import com.testsystem.back_java.dto.SubcategoryDto;
import com.testsystem.back_java.models.Category;
import com.testsystem.back_java.models.Subcategory;
import com.testsystem.back_java.repo.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public SubcategoryService(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    public List<Subcategory> findAllSubcategory(){
        List<Subcategory> subcategories = new ArrayList<>();
        subcategoryRepository.findAll().forEach(subcategories::add);
        return subcategories;
    }
    public Subcategory getSubcategory (long id){
        Optional<Subcategory> optionalSubcategory = subcategoryRepository.findById(id);
        if(optionalSubcategory.isPresent()) {return optionalSubcategory.get();}
        return null;
    }
    public void saveSubcategory(Subcategory subcategory){
        subcategoryRepository.save(subcategory);
    }

    public void updateSubcategory(Subcategory subcategory){
        subcategoryRepository.save(subcategory);
    }

    public void deleteSubcategory(long id){
        subcategoryRepository.deleteById(id);
    }
    public List<SubcategoryDto> findAllSubcategoryDto(){
        return subcategoryRepository.findAllSubcategoryDto();
    }

}
