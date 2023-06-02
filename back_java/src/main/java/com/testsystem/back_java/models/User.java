package com.testsystem.back_java.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity @Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity{

    @NonNull
    @Column(name = "login", nullable = false,unique = true)
    private String login;

    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @NonNull
    @Column(name = "email", nullable = false,unique = true)
    private String email;

    @NonNull
    @Column(name = "is_active", columnDefinition = "integer default 1")
    private Boolean isActive;

    @Nullable
    @Column(name = "name", nullable = true)
    private String name;

    @Nullable
    @Column(name = "surname", nullable = true)
    private String surname;

    @Nullable
    @Column(name = "birthday", nullable = true)
    private Date birthday;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Test> testSet;

}
