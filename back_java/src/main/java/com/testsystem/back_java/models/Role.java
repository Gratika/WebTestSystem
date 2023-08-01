package com.testsystem.back_java.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import deleted.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity @Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "roles")
public class Role extends BaseEntity{

    @NonNull
    @Column(name = "note", nullable = false, unique = true)
    private String note;

    @Column(name = "name")
    private String name;

    /*@OneToMany(mappedBy = "role")
    private List<UserRole> users;*/

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "id: " + super.getId() + ", " +
                "name: " + name+ ", " +
                "note: " + note + "}";
    }
}
