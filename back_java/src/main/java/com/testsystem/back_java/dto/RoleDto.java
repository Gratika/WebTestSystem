package com.testsystem.back_java.dto;

import com.testsystem.back_java.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    public RoleDto(Role role){
        this.id = role.getId();
        this.name = role.getName();
    }
    private Long id;
    private String name;

}
