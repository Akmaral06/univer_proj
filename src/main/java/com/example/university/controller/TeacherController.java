package com.example.university.controller;

import com.example.university.dto.TeacherDto;
import com.example.university.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService service;

    @PostMapping
    public TeacherDto create(@RequestBody TeacherDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<TeacherDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TeacherDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public TeacherDto update(@PathVariable Long id,
                             @RequestBody TeacherDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}