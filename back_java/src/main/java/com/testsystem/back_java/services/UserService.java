package com.testsystem.back_java.services;

import com.testsystem.back_java.models.User;

import java.util.List;

public interface UserService {
    User register (User user);
    List<User> getAll();
    User findUserById(long id);
    User findUserByLogin(String login);
    void delete (long id);
}
