package com.example.university.service.impl;

import com.example.university.dto.StudentDto;
import com.example.university.entity.Student;
import com.example.university.mapper.StudentMapper;
import com.example.university.repository.StudentRepository;
import com.example.university.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    @Override
    public StudentDto create(StudentDto dto) {
        Student saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    public StudentDto getById(Long id) {
        Student s = repository.findById(id).orElse(null);
        return s == null ? null : mapper.toDto(s);
    }

    @Override
    public List<StudentDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public StudentDto update(Long id, StudentDto dto) {
        Student s = repository.findById(id).orElse(null);
        if (s == null) return null;

        s.setName(dto.getName());
        s.setEmail(dto.getEmail());

        repository.save(s);

        return mapper.toDto(s);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
