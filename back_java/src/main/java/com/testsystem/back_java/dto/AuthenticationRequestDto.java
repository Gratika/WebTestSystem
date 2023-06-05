package com.testsystem.back_java.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequestDto {
    private String login;
    private String password;
    private String email;
}
