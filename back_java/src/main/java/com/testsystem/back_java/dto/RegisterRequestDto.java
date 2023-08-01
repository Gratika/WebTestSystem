package com.testsystem.back_java.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequestDto {
    private String login;
    private String email;
    private String password;
    private String passwordConfirm;

}
