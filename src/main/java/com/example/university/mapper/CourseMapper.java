package com.example.university.mapper;

import com.example.university.dto.CourseDto;
import com.example.university.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "teacherId", ignore = true)
    @Mapping(target = "studentIds", ignore = true)
    CourseDto toDto(Course course);

    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "students", ignore = true)
    Course toEntity(CourseDto dto);
}
