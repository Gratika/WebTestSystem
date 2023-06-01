package com.testsystem.back_java.repo;

import com.testsystem.back_java.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById(long id);
    User findUserByLogin(String login);
}
