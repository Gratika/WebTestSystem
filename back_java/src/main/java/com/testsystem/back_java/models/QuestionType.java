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
@Table(name = "question_types")
public class QuestionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    @NonNull
    @Column(name = "question_name",nullable = false)
    private String name;


    @Column(name = "question_note")
    private String note;

    @OneToMany(mappedBy = "questionType")
    Set<Question> questionSet;
}
