package com.example.university.mapper;

import com.example.university.dto.StudentDto;
import com.example.university.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "courseIds", ignore = true)
    StudentDto toDto(Student student);

    @Mapping(target = "courses", ignore = true)
    Student toEntity(StudentDto dto);
}
