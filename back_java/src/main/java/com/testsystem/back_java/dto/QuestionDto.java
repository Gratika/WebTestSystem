package com.testsystem.back_java.dto;

import com.testsystem.back_java.models.Answer;
import com.testsystem.back_java.models.Question;
import com.testsystem.back_java.models.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class QuestionDto {
    public QuestionDto(Question question){
        this.id = question.getId();
        this.text = question.getText();
        this.questionTypeDto = new QuestionTypeDto(question.getQuestionType());
        this.testId = question.getTest().getId();
        this.convertAnswerSet(question.getAnswerSet());

    }
    private long id;
    private String text;
    private QuestionTypeDto questionTypeDto;
    private long testId;
    List<AnswerDto> answerSetDto;

    private void convertAnswerSet( List<Answer> answerSet){
        this.answerSetDto = new ArrayList<>();
        answerSet.forEach((answer) ->{
            AnswerDto answerDto = new AnswerDto(answer);
            this.answerSetDto.add(answerDto);
        });
    }
}
