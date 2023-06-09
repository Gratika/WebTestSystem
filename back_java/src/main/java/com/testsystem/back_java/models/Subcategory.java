package com.testsystem.back_java.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;


@Entity @Data
@Table(name = "subcategories")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Subcategory extends BaseEntity{

   @NonNull
   @Column(name = "subcategory_name",nullable = true)
   private String name;

   @ManyToOne
   @JoinColumn(name = "category_id", nullable = false)
   private Category category;

   @OneToMany(mappedBy = "subcategory")
   private Set<Test> testSet;

}
