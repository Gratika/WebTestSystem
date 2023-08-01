package com.testsystem.back_java.dto;

import com.testsystem.back_java.models.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class QuestionTypeDto {
    public QuestionTypeDto(QuestionType type){
        this.id = type.getId();
        this.name = type.getName();
        this.note = type.getNote();
    }
    private long id;
    private String name;
    private String note;
}
