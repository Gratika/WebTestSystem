package com.testsystem.back_java.controllers;

import com.nimbusds.jose.JOSEException;
import com.testsystem.back_java.dto.*;
import com.testsystem.back_java.exception.UserAlreadyExistsException;
import com.testsystem.back_java.exception.UserNotFoundException;
import com.testsystem.back_java.models.User;
import com.testsystem.back_java.security.jwt.JwtUtils;
import com.testsystem.back_java.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final IUserService userService;

    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, IUserService userService) {
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
                throw new UserNotFoundException();
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtils.generateJwtToken(authentication);

            Map<Object, Object> response = new HashMap<>();
            response.put("login", username);
            response.put("token", token);
            return ResponseEntity.ok(response);


        }catch(AuthenticationException e) {
            throw new BadCredentialsException("Неправильний логін або пароль користувача");
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
   @RequestMapping(value = "/singup", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDto requestDto) throws UserAlreadyExistsException {
        String username = requestDto.getLogin();
        User user = userService.findUserByLogin(username);
        if(user!=null){
            throw new UserAlreadyExistsException();
        }
        user = new User(requestDto.getLogin(), requestDto.getEmail(), requestDto.getPassword());
        this.userService.register(user);

        return ResponseEntity.ok("Реєстрація пройшла успішно");
    }
    //@PreAuthorize("hasAnyRole('User_Role','Admin_Role', 'Teacher_Role')")
    @GetMapping("/currentUser")
    @Secured({"User_Role", "Admin_Role", "Teacher_Role"})
    public ResponseEntity<Map<Object, Object>> getCurrentUser(@RequestHeader("Authorization") String authorizationHeader) {
        log.info("метод getCurrentUser");
        // Извлекаем токен из заголовка Authorization
        String jwtToken = extractJwtTokenFromHeader(authorizationHeader);

        // Проверяем токен и извлекаем информацию о пользователе из токена
        String userName = jwtUtils.getUserNameFromJwtToken(jwtToken);

        UserDto curentUser = userService.findUserDtoByLogin(userName);
        if (curentUser==null) {
            throw new UserNotFoundException();
        };
        Map<Object, Object> response = new HashMap<>();
        response.put("user", curentUser);
        return ResponseEntity.ok(response);
    }
    // Метод для разлогинивания пользователя
    @GetMapping("/logout")
    @Secured({"User_Role", "Admin_Role", "Teacher_Role"})
    public ResponseEntity<GenericResponse> logoutUser() {
        // Получаем текущую аутентификацию пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}",authentication);
        GenericResponse res = new GenericResponse("Помилка","Неможливо виконати");
        // Если аутентификация не пустая, выполняем разлогинивание
        if (authentication != null) {
            // Очищаем текущую аутентификацию пользователя
            SecurityContextHolder.clearContext();
            res.setStatus("ОК");
            res.setMessage("Користувач вийшов з системи");
        }
        return ResponseEntity.ok(res);
    }
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public ResponseEntity<GenericResponse> addRole(@RequestBody AddRoleRequest addRoleRequest){
        userService.addRole(addRoleRequest.getUserId(),addRoleRequest.getRoleName());
        GenericResponse res = new GenericResponse("OK", "Роль успішно додано");
        return ResponseEntity.ok(res);
        
    }

    private String extractJwtTokenFromHeader(String authorizationHeader) {
        // Проверяем, что заголовок Authorization не пустой и содержит слово "Bearer"
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Извлекаем токен, удаляя префикс "Bearer "
            return authorizationHeader.substring(7);
        } else {
            throw new IllegalArgumentException("Неверный формат заголовка Authorization");
        }
    }
}
