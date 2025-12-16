package com.example.university.service;

import com.example.university.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto create(StudentDto dto);
    StudentDto getById(Long id);
    List<StudentDto> getAll();
    StudentDto update(Long id, StudentDto dto);
    void delete(Long id);
}
