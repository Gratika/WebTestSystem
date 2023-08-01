package com.testsystem.back_java.dto;

import com.testsystem.back_java.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
    private long id;
    private String login;
    private String email;
    public  OwnerDto(User user){
        this.id=user.getId();
        this.login = user.getLogin();
        this.email = user.getEmail();
    }
}
