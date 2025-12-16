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

    public StudentDto create(StudentDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public StudentDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    public List<StudentDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public StudentDto update(Long id, StudentDto dto) {
        Student s = repository.findById(id).orElse(null);
        s.setName(dto.getName());
        s.setEmail(dto.getEmail());
        return mapper.toDto(s);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
