package com.testsystem.back_java.repo;

import com.testsystem.back_java.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {

}
