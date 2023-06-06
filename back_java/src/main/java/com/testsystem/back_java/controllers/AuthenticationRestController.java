package com.testsystem.back_java.controllers;

import com.testsystem.back_java.dto.AuthenticationRequestDto;
import com.testsystem.back_java.exception.UserAlreadyExists;
import com.testsystem.back_java.models.User;
import com.testsystem.back_java.security.JwtUserDetailsService;
import com.testsystem.back_java.security.jwt.JwtTokenProvider;
import com.testsystem.back_java.security.jwt.JwtUser;
import com.testsystem.back_java.services.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUserDetailsService userService;

    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, JwtUserDetailsService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto){
        try{
            String username = requestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            //User user = userService.findUserByLogin(username);
            UserDetails user = userService.loadUserByUsername(username);
            if(user==null){
                throw new UsernameNotFoundException("User with username: "+username+" not found");
            }
            //String token = jwtTokenProvider.createToken(username, user.getRoles());
            String token = jwtTokenProvider.generateToken(user);
            Map<Object, Object> response = new HashMap<>();
            response.put("login", username);
            response.put("token", token);
            return ResponseEntity.ok(response);


        }catch(AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }
   /* @RequestMapping(value = "/singup")
    public ResponseEntity singup(AuthenticationRequestDto requestDto){
        String username = requestDto.getLogin();
        UserDetails user = userService.loadUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User with username: "+username+" not found");
        }
        user = new User(requestDto.getLogin(), requestDto.getEmail(), requestDto.getPassword());
        User registerUser = this.userService.register(user);
        String token = jwtTokenProvider.generateToken(userService);
        //String token = jwtTokenProvider.createToken(registerUser.getLogin(), registerUser.getRoles());
        Map<Object, Object> response = new HashMap<>();
        response.put("login", registerUser.getLogin());
        response.put("token", token);
        return ResponseEntity.ok(response);
    }*/
}
