package com.testsystem.back_java.repo;

import com.testsystem.back_java.models.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
