package com.example.university.controller;

import com.example.university.dto.TeacherDto;
import com.example.university.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService service;

    @PostMapping
    public ResponseEntity<TeacherDto> create(@RequestBody TeacherDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getById(@PathVariable Long id) {
        TeacherDto dto = service.getById(id);
        return dto == null ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> update(@PathVariable Long id,
                                             @RequestBody TeacherDto dto) {
        TeacherDto updated = service.update(id, dto);
        return updated == null ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
