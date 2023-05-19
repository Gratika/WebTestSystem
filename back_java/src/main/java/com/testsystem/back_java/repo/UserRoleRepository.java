package com.testsystem.back_java.repo;

import com.testsystem.back_java.models.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
