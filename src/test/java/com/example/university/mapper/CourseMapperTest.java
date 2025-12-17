package com.example.university.mapper;

import com.example.university.dto.CourseDto;
import com.example.university.entity.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CourseMapperTest {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    void convertEntityToDtoTest() {

        Course course = new Course();
        course.setId(1L);
        course.setTitle("Math");
        course.setCredits(5);

        CourseDto dto = courseMapper.toDto(course);

        Assertions.assertNotNull(dto);

        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getTitle());
        Assertions.assertNotNull(dto.getCredits());

        Assertions.assertEquals(course.getId(), dto.getId());
        Assertions.assertEquals(course.getTitle(), dto.getTitle());
        Assertions.assertEquals(course.getCredits(), dto.getCredits());
    }

    @Test
    void convertDtoToEntityTest() {

        CourseDto dto = new CourseDto();
        dto.setId(1L);
        dto.setTitle("Physics");
        dto.setCredits(4);

        Course course = courseMapper.toEntity(dto);

        Assertions.assertNotNull(course);

        Assertions.assertNotNull(course.getId());
        Assertions.assertNotNull(course.getTitle());
        Assertions.assertNotNull(course.getCredits());

        Assertions.assertEquals(dto.getId(), course.getId());
        Assertions.assertEquals(dto.getTitle(), course.getTitle());
        Assertions.assertEquals(dto.getCredits(), course.getCredits());
    }

    @Test
    void convertEntityListToDtoListTest() {

        List<Course> courseList = new ArrayList<>();
        Course c1 = new Course();
        c1.setId(1L);
        c1.setTitle("Math");
        c1.setCredits(5);

        Course c2 = new Course();
        c2.setId(2L);
        c2.setTitle("Physics");
        c2.setCredits(4);

        courseList.add(c1);
        courseList.add(c2);

        List<CourseDto> dtoList = courseMapper.toDtoList(courseList);

        Assertions.assertNotNull(dtoList);
        Assertions.assertNotEquals(0, dtoList.size());
        Assertions.assertEquals(courseList.size(), dtoList.size());

        for (int i = 0; i < dtoList.size(); i++) {
            Assertions.assertNotNull(dtoList.get(i));
            Assertions.assertEquals(courseList.get(i).getId(), dtoList.get(i).getId());
            Assertions.assertEquals(courseList.get(i).getTitle(), dtoList.get(i).getTitle());
            Assertions.assertEquals(courseList.get(i).getCredits(), dtoList.get(i).getCredits());
        }
    }
}
