package com.example.university.controller;

import com.example.university.dto.StudentDto;
import com.example.university.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {


    private final StudentService service;

    @PostMapping
    public StudentDto create(@RequestBody StudentDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<StudentDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public StudentDto get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public StudentDto update(@PathVariable Long id, @RequestBody StudentDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
