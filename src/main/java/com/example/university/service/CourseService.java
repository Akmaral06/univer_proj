package com.example.university.service;

import com.example.university.dto.CourseDto;

import java.util.List;

public interface CourseService {

    CourseDto create(CourseDto dto);

    CourseDto getById(Long id);

    List<CourseDto> getAll();

    CourseDto update(Long id, CourseDto dto);

    void delete(Long id);

    CourseDto addStudent(Long courseId, Long studentId);

    CourseDto removeStudent(Long courseId, Long studentId);
}
