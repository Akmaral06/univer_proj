package com.example.university.service;

import com.example.university.dto.CourseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Test
    void getAllTest() {

        List<CourseDto> list = courseService.getAll();

        Assertions.assertNotNull(list);
        Assertions.assertNotEquals(0, list.size());

        for (CourseDto courseDto : list) {
            Assertions.assertNotNull(courseDto);
            Assertions.assertNotNull(courseDto.getId());
            Assertions.assertNotNull(courseDto.getTitle());
        }
    }

    @Test
    void getByIdTest() {

        Random random = new Random();

        List<CourseDto> list = courseService.getAll();
        int randomIndex = random.nextInt(list.size());
        Long someId = list.get(randomIndex).getId();

        CourseDto courseDto = courseService.getById(someId);

        Assertions.assertNotNull(courseDto);
        Assertions.assertNotNull(courseDto.getId());
        Assertions.assertNotNull(courseDto.getTitle());

        CourseDto notFound = courseService.getById(-1L);
        Assertions.assertNull(notFound);
    }

    @Test
    void createCourseTest() {

        CourseDto dto = new CourseDto();
        dto.setTitle("Test Course");

        CourseDto created = courseService.create(dto);

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getTitle());

        Assertions.assertEquals(dto.getTitle(), created.getTitle());

        CourseDto fromDb = courseService.getById(created.getId());

        Assertions.assertNotNull(fromDb);
        Assertions.assertEquals(created.getId(), fromDb.getId());
        Assertions.assertEquals(created.getTitle(), fromDb.getTitle());
    }

    @Test
    void updateCourseTest() {

        Random random = new Random();
        List<CourseDto> list = courseService.getAll();

        Long someId = list.get(random.nextInt(list.size())).getId();

        CourseDto dto = new CourseDto();
        dto.setId(someId);
        dto.setTitle("Updated Course");

        CourseDto updated = courseService.update(someId, dto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(dto.getId(), updated.getId());
        Assertions.assertEquals(dto.getTitle(), updated.getTitle());

        CourseDto check = courseService.getById(someId);

        Assertions.assertNotNull(check);
        Assertions.assertEquals(updated.getTitle(), check.getTitle());
    }

    @Test
    void deleteCourseTest() {

        Random random = new Random();
        List<CourseDto> list = courseService.getAll();

        Long someId = list.get(random.nextInt(list.size())).getId();

        courseService.delete(someId);

        CourseDto check = courseService.getById(someId);
        Assertions.assertNull(check);
    }

}
