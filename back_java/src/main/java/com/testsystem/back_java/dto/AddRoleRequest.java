package com.testsystem.back_java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddRoleRequest {
    private Long userId;
    private String roleName;
}
