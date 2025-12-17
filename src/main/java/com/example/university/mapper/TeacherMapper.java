package com.example.university.mapper;

import com.example.university.dto.TeacherDto;
import com.example.university.entity.Teacher;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDto toDto(Teacher teacher);

    Teacher toEntity(TeacherDto dto);

    List<TeacherDto> toDtoList(List<Teacher> teachers);
}
