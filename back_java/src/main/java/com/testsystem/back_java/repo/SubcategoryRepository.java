package com.testsystem.back_java.repo;

import com.testsystem.back_java.dto.SubcategoryDto;
import com.testsystem.back_java.models.Subcategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubcategoryRepository extends CrudRepository<Subcategory, Long> {


}
