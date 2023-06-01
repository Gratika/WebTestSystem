package com.testsystem.back_java.services.impl;

import com.testsystem.back_java.models.User;
import com.testsystem.back_java.repo.RoleRepository;
import com.testsystem.back_java.repo.UserRepository;
import com.testsystem.back_java.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public User register(User user) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User findUserById(long id) {
        return null;
    }

    @Override
    public User findUserByLogin(String login) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
