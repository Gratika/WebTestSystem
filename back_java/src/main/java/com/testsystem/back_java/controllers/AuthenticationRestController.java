package com.testsystem.back_java.controllers;

import com.testsystem.back_java.dto.LoginRequestDto;
import com.testsystem.back_java.dto.RegisterRequestDto;
import com.testsystem.back_java.exception.UserAlreadyExists;
import com.testsystem.back_java.models.User;
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

@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
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
    public ResponseEntity<Map<Object, Object>> login(@RequestBody LoginRequestDto requestDto){
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
            throw new BadCredentialsException("Неправильний логін або пароль користувача");
        }
    }
   @RequestMapping(value = "/singup", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDto requestDto){
        String username = requestDto.getLogin();
        User user = userService.findUserByLogin(username);
        if(user!=null){
            throw new UserAlreadyExists("Користувач: "+username+" вже зареєстрований в системі");
        }
        user = new User(requestDto.getLogin(), requestDto.getEmail(), requestDto.getPassword());
        this.userService.register(user);

        return ResponseEntity.ok("Реєстрація пройшла успішно");
    }
}
