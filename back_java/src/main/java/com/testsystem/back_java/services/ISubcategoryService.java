package com.testsystem.back_java.services;

import com.testsystem.back_java.dto.SubcategoryDto;
import com.testsystem.back_java.models.Subcategory;

import java.util.List;

public interface ISubcategoryService {
    public List<Subcategory> findAllSubcategory();
    public Subcategory getSubcategory (long id);
    public Subcategory saveSubcategory(Subcategory subcategory);
    public void updateSubcategory(Subcategory subcategory);
    public void deleteSubcategory(long id);
    public List<SubcategoryDto> findAllSubcategoryDto();
    SubcategoryDto convertEntityToDto(Subcategory subcategory);
    public Subcategory convertSubcategoryToEntity(SubcategoryDto subcategoryDto);

}
