package com.testsystem.back_java.repo;

import com.testsystem.back_java.models.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question,Long> {
}
