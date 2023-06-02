package com.testsystem.back_java.services.impl;

import com.testsystem.back_java.models.UserRole;
import com.testsystem.back_java.repo.UserRoleRepository;
import com.testsystem.back_java.services.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    //можливо краще вилучити
    private UserRoleRepository userRoleRepository;

    @Override
    public void save(UserRole userRole) {
        this.userRoleRepository.save(userRole);
    }
}
