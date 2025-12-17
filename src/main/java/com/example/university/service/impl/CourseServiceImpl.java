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

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseDto create(CourseDto dto) {
        Course course = courseMapper.toEntity(dto);

        if (dto.getTeacherId() != null) {
            Teacher teacher = teacherRepository
                    .findById(dto.getTeacherId())
                    .orElse(null);
            course.setTeacher(teacher);
        }

        Course saved = courseRepository.save(course);
        return courseMapper.toDto(saved);
    }

    @Override
    public CourseDto getById(Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<CourseDto> getAll() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDto)
                .toList();
    }

    @Override
    public CourseDto update(Long id, CourseDto dto) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) return null;

        course.setTitle(dto.getTitle());
        course.setCredits(dto.getCredits());

        if (dto.getTeacherId() != null) {
            Teacher teacher = teacherRepository
                    .findById(dto.getTeacherId())
                    .orElse(null);
            course.setTeacher(teacher);
        } else {
            course.setTeacher(null);
        }

        Course saved = courseRepository.save(course);
        return courseMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseDto addStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);

        if (course == null || student == null) return null;

        student.getCourses().add(course);
        studentRepository.save(student);

        return courseMapper.toDto(course);
    }

    @Override
    public CourseDto removeStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);

        if (course == null || student == null) return null;

        student.getCourses().remove(course);
        studentRepository.save(student);

        return courseMapper.toDto(course);
    }
}
