package com.testsystem.back_java.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @NonNull
    @Column(name = "answer_text",nullable = false)
    private String text;

    @NonNull
    @Column(name = "is_right", columnDefinition = "integer default 0")
    private Boolean isRight;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}
