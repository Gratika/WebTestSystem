package com.testsystem.back_java.security;

import com.testsystem.back_java.models.User;
import com.testsystem.back_java.security.jwt.JwtUser;
import com.testsystem.back_java.security.jwt.JwtUserFactory;
import com.testsystem.back_java.services.UserService;
import com.testsystem.back_java.services.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final UserServiceImpl userService;

    @Autowired
    public JwtUserDetailsService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByLogin(username);
        if (user==null){
            throw new UsernameNotFoundException("User with login: "+username+" not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
