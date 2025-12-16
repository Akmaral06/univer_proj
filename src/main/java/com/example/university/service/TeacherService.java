package com.example.university.service;

import com.example.university.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    TeacherDto create(TeacherDto dto);
    TeacherDto getById(Long id);
    List<TeacherDto> getAll();
    TeacherDto update(Long id, TeacherDto dto);
    void delete(Long id);
}
