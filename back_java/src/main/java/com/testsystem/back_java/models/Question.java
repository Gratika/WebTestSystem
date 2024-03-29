package com.testsystem.back_java.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "questions")
public class Question extends BaseEntity{

    @NonNull
    @Column(name = "text",nullable = false)
    private String text;

    //private String image;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private QuestionType questionType;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @OneToMany(mappedBy = "question")
    List<Answer> answerSet;

}
