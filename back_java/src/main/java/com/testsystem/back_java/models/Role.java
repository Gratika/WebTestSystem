package com.testsystem.back_java.models;

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
    @Column(name = "abbr", nullable = false, unique = true)
    private String abbr;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "id: " + super.getId() + ", " +
                "name: " + name +
                "abbr" + abbr + "}";
    }
}
