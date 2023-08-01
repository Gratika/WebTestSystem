package com.testsystem.back_java.services.impl;

import com.testsystem.back_java.dto.SubcategoryDto;
import com.testsystem.back_java.models.Subcategory;
import com.testsystem.back_java.repo.SubcategoryRepository;
import com.testsystem.back_java.services.ISubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubcategoryServiceImpl implements ISubcategoryService {

    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository) {
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
    public Subcategory saveSubcategory(Subcategory subcategory){
        Subcategory saveSubcategory =subcategoryRepository.save(subcategory);
        return saveSubcategory;
    }

    @Override
    public Subcategory convertSubcategoryToEntity(SubcategoryDto subcategoryDto) {
        Subcategory subcategory = new Subcategory();
        subcategory.setName(subcategoryDto.getName());
        return subcategory;

    }

    public void updateSubcategory(Subcategory subcategory){
        subcategoryRepository.save(subcategory);
    }

    public void deleteSubcategory(long id){
        subcategoryRepository.deleteById(id);
    }
    public List<SubcategoryDto> findAllSubcategoryDto(){

        return this.findAllSubcategory()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public SubcategoryDto convertEntityToDto(Subcategory subcategory){
        SubcategoryDto subcategoryDto = new SubcategoryDto();
        subcategoryDto.setId(subcategory.getId());
        subcategoryDto.setName(subcategory.getName());
        subcategoryDto.setCategoryId(subcategory.getCategory().getId());
        return subcategoryDto;
    }

}
