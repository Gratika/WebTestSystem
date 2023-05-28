package com.testsystem.back_java.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Entity @Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NonNull
    @Column(name = "category_name", nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    Set<Subcategory> subcategorySet;

}
