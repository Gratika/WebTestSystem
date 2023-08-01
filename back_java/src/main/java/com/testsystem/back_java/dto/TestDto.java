package com.testsystem.back_java.dto;

import com.testsystem.back_java.models.Question;
import com.testsystem.back_java.models.Test;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDto {
    private long id;
    private String name;
    private Boolean isPublic;
    private String description;
    private OwnerDto owner;
    private long subcategoryId;
    private List<QuestionDto> questionDtoList;
    public TestDto(Test test){
        this.id=test.getId();
        this.name = test.getName();
        this.isPublic = test.getIsPublic();
        this.description=test.getDescription();
        this.subcategoryId = test.getSubcategory().getId();
        this.owner = new OwnerDto(test.getOwner());
        this.convertQuestionList(test.getQuestionSet());
    }
    private void convertQuestionList(List<Question> questions){
        this.questionDtoList = new ArrayList<>();
        questions.forEach(question->{
            QuestionDto questionDto = new QuestionDto(question);
            this.questionDtoList.add(questionDto);
        });
    }
}
