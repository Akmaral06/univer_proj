package com.example.university.dto;

import lombok.Data;

import java.util.Set;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private Set<Long> courseIds;
}

