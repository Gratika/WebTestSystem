package com.testsystem.back_java.controllers;

import com.testsystem.back_java.dto.AuthenticationRequestDto;
import com.testsystem.back_java.models.User;
import com.testsystem.back_java.security.jwt.JwtTokenProvider;
import com.testsystem.back_java.security.jwt.JwtUtils;
import com.testsystem.back_java.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils=jwtUtils;
        this.userService = userService;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthenticationRequestDto requestDto){
        try{
            String username = requestDto.getLogin();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findUserByLogin(username);

            if(user==null){
                throw new UsernameNotFoundException("User with username: "+username+" not found");
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtils.generateJwtToken(authentication);

            Map<Object, Object> response = new HashMap<>();
            response.put("login", username);
            response.put("token", token);
            return ResponseEntity.ok(response);


        }catch(AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }
   @RequestMapping(value = "/singup", method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@RequestBody AuthenticationRequestDto requestDto){
        String username = requestDto.getLogin();
        User user = userService.findUserByLogin(username);
        if(user==null){
            throw new UsernameNotFoundException("User with username: "+username+" not found");
        }
        user = new User(requestDto.getLogin(), requestDto.getEmail(), requestDto.getPassword());
        User registerUser = this.userService.register(user);

        return ResponseEntity.ok(registerUser);
    }
}