package com.testsystem.back_java.dto;

import com.testsystem.back_java.models.BaseEntity;
import com.testsystem.back_java.models.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    public UserDto(User user){
        this.id = user.getId();
        this.login = user.getLogin();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.birthday = user.getBirthday();
        this.isActive = user.getIsActive();
        this.created = user.getCreated();
        this.updated = user.getUpdated();
        this.roles = new ArrayList<>();
        user.getRoles().forEach(role -> roles.add(new RoleDto(role)));
    }
    private Long id;
    private String login;
    private String password;
    private String email;
    private Boolean isActive;
    private String name;
    private String surname;
    private Date birthday;
    private List<RoleDto>roles;
    private Date created;
    private Date updated;
}
