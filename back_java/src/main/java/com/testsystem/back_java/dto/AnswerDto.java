package com.testsystem.back_java.dto;

import com.testsystem.back_java.models.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AnswerDto {
    public AnswerDto(Answer answer){
        this.id = answer.getId();
        this.text = answer.getText();
        this.isRight = answer.getIsRight();
        this.questionId = answer.getQuestion().getId();
    }
    private long id;
    private String text;
    private Boolean isRight;
    private long questionId;
}
