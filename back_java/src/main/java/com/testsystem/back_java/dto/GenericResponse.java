package com.testsystem.back_java.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponse {
    private  String status;
    private  String message;
}
