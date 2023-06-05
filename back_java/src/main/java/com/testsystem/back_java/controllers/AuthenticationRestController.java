package com.testsystem.back_java.controllers;

import com.testsystem.back_java.dto.AuthenticationRequestDto;
import com.testsystem.back_java.exception.UserAlreadyExists;
import com.testsystem.back_java.models.User;
import com.testsystem.back_java.security.jwt.JwtTokenProvider;
import com.testsystem.back_java.services.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserServiceImpl userService;

    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserServiceImpl userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }
    @RequestMapping(value = "/login")
    public ResponseEntity login(AuthenticationRequestDto requestDto){
        try{
            String username = requestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findUserByLogin(username);
            if(user==null){
                throw new UsernameNotFoundException("User with username: "+username+" not found");
            }
            String token = jwtTokenProvider.createToken(username, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("login", username);
            response.put("token", token);
            return ResponseEntity.ok(response);


        }catch(AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    @RequestMapping(value = "/singup")
    public ResponseEntity singup(AuthenticationRequestDto requestDto){
        String username = requestDto.getLogin();
        User user = userService.findUserByLogin(username);
        if(user!=null){
            throw new UserAlreadyExists("The user with login "+username+" already exists");
        }
        user = new User(requestDto.getLogin(), requestDto.getEmail(), requestDto.getPassword());
        User registerUser = this.userService.register(user);
        String token = jwtTokenProvider.createToken(registerUser.getLogin(), registerUser.getRoles());
        Map<Object, Object> response = new HashMap<>();
        response.put("login", registerUser.getLogin());
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
