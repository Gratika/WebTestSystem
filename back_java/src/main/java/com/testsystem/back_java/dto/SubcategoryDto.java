package com.testsystem.back_java.dto;

import com.testsystem.back_java.models.Category;
import com.testsystem.back_java.models.Test;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
public class SubcategoryDto {
    private Long id;
    private String name;
    private Long categoryId;
}
