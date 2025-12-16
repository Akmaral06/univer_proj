package com.example.university.controller;

import com.example.university.dto.CourseDto;
import com.example.university.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @PostMapping
    public CourseDto create(@RequestBody CourseDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<CourseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CourseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public CourseDto update(@PathVariable Long id,
                            @RequestBody CourseDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/{courseId}/students/{studentId}")
    public CourseDto addStudent(@PathVariable Long courseId,
                                @PathVariable Long studentId) {
        return service.addStudent(courseId, studentId);
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public CourseDto removeStudent(@PathVariable Long courseId,
                                   @PathVariable Long studentId) {
        return service.removeStudent(courseId, studentId);
    }
}