package com.testsystem.back_java.services.impl;

import com.testsystem.back_java.dto.SubcategoryDto;
import com.testsystem.back_java.dto.TestDto;
import com.testsystem.back_java.models.Subcategory;
import com.testsystem.back_java.models.Test;

import java.util.List;

public interface ITestService {
    public List<Test> findAllTest();
    public Test getTestById (long id);
    public Test saveTest(Test test);
    public void updateTest(Test test);
    public void deleteTest(long id);
    public Test convertTestDtoToEntity(TestDto testDto) throws Exception;
    /*public List<SubcategoryDto> findAllSubcategoryDto();
    SubcategoryDto convertEntityToDto(Subcategory subcategory);
   */
}
