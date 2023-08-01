package com.testsystem.back_java.security;

import com.testsystem.back_java.exception.UserNotFoundException;
import com.testsystem.back_java.models.User;
import com.testsystem.back_java.security.jwt.UserDetailsImpl;
import com.testsystem.back_java.security.jwt.JwtUserFactory;
import com.testsystem.back_java.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    private final IUserService IUserService;

    //використала анотацію @Lazy щоб обійти так звану циклічну залежність
    @Autowired
    public UserDetailsServiceImpl(@Lazy IUserService IUserService) {
        this.IUserService = IUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        User user = IUserService.findUserByLogin(username);
        if (user==null){
            throw new UserNotFoundException();
        }
        UserDetailsImpl jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        log.info("IN loadUserByUsername - jwtUser: {}" , jwtUser);
        return jwtUser;
    }
}
