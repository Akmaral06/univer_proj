package com.example.university.service;

import com.example.university.dto.CourseDto;
import com.example.university.entity.Course;
import com.example.university.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    private Long baseCourseId;

    @BeforeEach
    void initData() {
        if (courseRepository.count() == 0) {
            Course course = new Course();
            course.setTitle("Base course");
            course.setCredits(5);
            courseRepository.save(course);
        }

        baseCourseId = courseRepository.findAll().get(0).getId();
        assertNotNull(baseCourseId);
    }

    @Test
    void createCourseTest() {
        CourseDto dto = new CourseDto();
        dto.setTitle("Algorithms");
        dto.setCredits(6);

        CourseDto saved = courseService.create(dto);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("Algorithms", saved.getTitle());
        assertEquals(6, saved.getCredits());
    }

    @Test
    void getByIdTest() {
        CourseDto found = courseService.getById(baseCourseId);

        assertNotNull(found);
        assertEquals(baseCourseId, found.getId());
    }

    @Test
    void getAllTest() {
        List<CourseDto> courses = courseService.getAll();

        assertNotNull(courses);
        assertFalse(courses.isEmpty(), "The list of courses must not be empty");
    }

    @Test
    void updateCourseTest() {
        CourseDto dto = new CourseDto();
        dto.setTitle("Updated title");
        dto.setCredits(10);

        CourseDto updated = courseService.update(baseCourseId, dto);

        assertNotNull(updated);
        assertEquals(baseCourseId, updated.getId());
        assertEquals("Updated title", updated.getTitle());
        assertEquals(10, updated.getCredits());
    }

    @Test
    void deleteCourseTest() {
        Course course = new Course();
        course.setTitle("To delete");
        course.setCredits(1);
        course = courseRepository.save(course);

        Long id = course.getId();
        assertNotNull(id);

        courseService.delete(id);

        assertTrue(courseRepository.findById(id).isEmpty());
    }
}
