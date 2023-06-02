package com.testsystem.back_java.services.impl;

import com.testsystem.back_java.models.Role;
import com.testsystem.back_java.models.User;
import com.testsystem.back_java.models.UserRole;
import com.testsystem.back_java.repo.RoleRepository;
import com.testsystem.back_java.repo.UserRepository;
import com.testsystem.back_java.repo.UserRoleRepository;
import com.testsystem.back_java.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,  BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role newRole = roleRepository.findRoleByAbbr("User_Role");
        List<Role> roles = new ArrayList<>();
        roles.add(newRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));//шифруємо пароль
        user.setRoles(roles);
        User registerUser =this.userRepository.save(user);
        log.info("In register - user: {} successfully registered", registerUser);
        return registerUser;

    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        this.userRepository.findAll().forEach(userList::add);
        log.info("In get all: found {} users",userList.size());
        return userList;
    }

    @Override
    public User findUserById(long id) {
        User findUser = this.userRepository.findUserById(id);
        if (findUser==null)
        {
            log.info("In findUserById - user not found by id {}", id);
        }else{
            log.info("In findUserById - user {} found by id {}", findUser,id);
        }

        return findUser;
    }

    @Override
    public User findUserByLogin(String login) {
        User findUser = this.userRepository.findUserByLogin(login);
        if (findUser==null)
        {
            log.info("In findUserById - user not found by login {}", login);
        }else{
            log.info("In findUserById - user {} found by login {}", findUser,login);
        }
        return findUser;
    }

    @Override
    public void delete(User user) {
        this.userRepository.delete(user);
        log.info("In delete - User {} successfully delete", user);
    }

    @Override
    public void deleteById(long id) {
        this.userRepository.deleteById(id);
        log.info("In deleteById - User with id {} successfully delete", id);

    }
}
