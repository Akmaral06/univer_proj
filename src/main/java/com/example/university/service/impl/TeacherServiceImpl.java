package com.example.university.service.impl;

import com.example.university.dto.TeacherDto;
import com.example.university.entity.Teacher;
import com.example.university.mapper.TeacherMapper;
import com.example.university.repository.TeacherRepository;
import com.example.university.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;
    private final TeacherMapper mapper;

    @Override
    public TeacherDto create(TeacherDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public TeacherDto getById(Long id) {
        Teacher teacher = repository.findById(id).orElse(null);
        return teacher == null ? null : mapper.toDto(teacher);
    }

    @Override
    public List<TeacherDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public TeacherDto update(Long id, TeacherDto dto) {
        Teacher teacher = repository.findById(id).orElse(null);
        if (teacher == null) return null;

        teacher.setName(dto.getName());
        teacher.setDepartment(dto.getDepartment());

        repository.save(teacher);

        return mapper.toDto(teacher);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
