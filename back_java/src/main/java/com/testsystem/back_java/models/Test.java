package com.testsystem.back_java.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "tests")
public class Test extends BaseEntity{

    @NonNull
    @Column(name = "test_name",nullable = false)
    private String name;

    @NonNull
    @Column(name = "is_public", columnDefinition = "integer default 1")
    private Boolean isPublic;

    @Column(name = "description", nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    private Subcategory subcategory;

    @OneToMany(mappedBy = "test")
    Set<Question> questionSet;
}
