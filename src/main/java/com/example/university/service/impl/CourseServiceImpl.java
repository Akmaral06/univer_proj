package com.example.university.service.impl;

import com.example.university.dto.CourseDto;
import com.example.university.entity.Course;
import com.example.university.entity.Student;
import com.example.university.entity.Teacher;
import com.example.university.mapper.CourseMapper;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.StudentRepository;
import com.example.university.repository.TeacherRepository;
import com.example.university.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final CourseMapper mapper;

    @Override
    public CourseDto create(CourseDto dto) {
        Course course = mapper.toEntity(dto);
        Teacher teacher = teacherRepository.findById(dto.getTeacherId()).orElse(null);
        course.setTeacher(teacher);
        return mapper.toDto(courseRepository.save(course));
    }

    @Override
    public CourseDto getById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);

        if (course == null) {
            return null;
        }

        CourseDto dto = mapper.toDto(course);

        if (course.getTeacher() != null) {
            dto.setTeacherId(course.getTeacher().getId());
        }

        dto.setStudentIds(
                course.getStudents()
                        .stream()
                        .map(student -> student.getId())
                        .collect(Collectors.toSet())
        );

        return dto;
    }


    @Override
    public List<CourseDto> getAll() {
        return courseRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public CourseDto update(Long id, CourseDto dto) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) return null;

        course.setTitle(dto.getTitle());
        course.setCredits(dto.getCredits());

        Teacher teacher = teacherRepository.findById(dto.getTeacherId()).orElse(null);
        course.setTeacher(teacher);

        return mapper.toDto(course);
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseDto addStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);

        if (course != null && student != null) {
            course.getStudents().add(student);
            student.getCourses().add(course);
        }
        return mapper.toDto(course);
    }

    @Override
    public CourseDto removeStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);

        if (course != null && student != null) {
            course.getStudents().remove(student);
            student.getCourses().remove(course);
        }
        return mapper.toDto(course);
    }
}
