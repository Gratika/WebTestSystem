package com.testsystem.back_java.services;

import com.testsystem.back_java.dto.UserDto;
import com.testsystem.back_java.models.User;

import java.util.List;

public interface IUserService {
    User register (User user);
    List<User> getAll();
    User findUserById(long id);
    User findUserByLogin(String login);
    void delete (User user);
    void deleteById (long id);
    void addRole (long userId, String roleName);
    UserDto findUserDtoByLogin(String login);
}
