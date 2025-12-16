package com.example.university.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CourseDto {
    private Long id;
    private String title;
    private int credits;
    private Long teacherId;
    private Set<Long> studentIds;
}
