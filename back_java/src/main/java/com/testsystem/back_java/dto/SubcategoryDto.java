package com.testsystem.back_java.dto;

import com.testsystem.back_java.models.Category;
import com.testsystem.back_java.models.Test;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubcategoryDto {
    private Long id;
    private String name;
    private Long categoryId;
}
