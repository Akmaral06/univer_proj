package com.example.university.controller;

import com.example.university.dto.CourseDto;
import com.example.university.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @PostMapping
    public ResponseEntity<CourseDto> create(@RequestBody CourseDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable Long id) {
        CourseDto dto = service.getById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> update(@PathVariable Long id,
                                            @RequestBody CourseDto dto) {
        CourseDto updated = service.update(id, dto);
        return updated == null ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<CourseDto> addStudent(@PathVariable Long courseId,
                                                @PathVariable Long studentId) {
        CourseDto dto = service.addStudent(courseId, studentId);
        return dto == null ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<CourseDto> removeStudent(@PathVariable Long courseId,
                                                   @PathVariable Long studentId) {
        CourseDto dto = service.removeStudent(courseId, studentId);
        return dto == null ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(dto);
    }
}
