package com.testsystem.back_java.dto;

import com.testsystem.back_java.models.Test;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    public TestDto(Test test){
        this.id=test.getId();
        this.name = test.getName();
        this.isPublic = test.getIsPublic();
        this.description=test.getDescription();
        this.subcategoryId = test.getSubcategory().getId();
        this.owner = new OwnerDto(test.getOwner());
    }
}
