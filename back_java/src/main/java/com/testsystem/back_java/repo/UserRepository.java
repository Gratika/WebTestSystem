package com.testsystem.back_java.repo;

import com.testsystem.back_java.models.Role;
import com.testsystem.back_java.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById(long id);
    User findUserByLogin(String login);

    @Modifying
    @Query("UPDATE User u SET u.roles = :roles WHERE u.id = :userId")
    void addRolesToUser(@Param("userId") Long userId, @Param("roles") List<Role> roles);



}
