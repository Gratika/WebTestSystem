package com.testsystem.back_java.repo;

import com.testsystem.back_java.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findRoleById(long id);
    Role findRoleByAbbr(String abbr);
}
