package com.testsystem.back_java.security;

import com.testsystem.back_java.models.User;
import com.testsystem.back_java.security.jwt.UserDetailsImpl;
import com.testsystem.back_java.security.jwt.JwtUserFactory;
import com.testsystem.back_java.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserService userService;

    //використала анотацію @Lazy щоб обійти так звану циклічну залежність
    @Autowired
    public UserDetailsServiceImpl(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByLogin(username);
        if (user==null){
            throw new UsernameNotFoundException("User with login: "+username+" not found");
        }
        UserDetailsImpl jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
