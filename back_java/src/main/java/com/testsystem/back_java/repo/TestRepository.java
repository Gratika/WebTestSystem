package com.testsystem.back_java.repo;

import com.testsystem.back_java.models.Test;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Test, Long> {
}
