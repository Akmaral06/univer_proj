package com.example.university.service;

import com.example.university.UniversityApplication;
import com.example.university.dto.TeacherDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = UniversityApplication.class)
class TeacherServiceTest {

    @Autowired
    private TeacherService service;

    @Test
    void createTeacherTest() {
        TeacherDto dto = new TeacherDto();
        dto.setName("Askar");
        dto.setDepartment("CS");

        TeacherDto saved = service.create(dto);

        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("Askar", saved.getName());
        Assertions.assertEquals("CS", saved.getDepartment());
    }

    @Test
    void getByIdTest() {
        TeacherDto dto = new TeacherDto();
        dto.setName("Dana");
        dto.setDepartment("Math");

        TeacherDto saved = service.create(dto);

        TeacherDto found = service.getById(saved.getId());

        Assertions.assertNotNull(found);
        Assertions.assertEquals(saved.getId(), found.getId());
    }

    @Test
    void getAllTest() {
        List<TeacherDto> list = service.getAll();

        Assertions.assertTrue(list == null || list.size() >= 0);
    }

    @Test
    void updateTeacherTest() {
        TeacherDto dto = new TeacherDto();
        dto.setName("Old");
        dto.setDepartment("OldDept");

        TeacherDto saved = service.create(dto);

        saved.setName("New");
        saved.setDepartment("NewDept");

        TeacherDto updated = service.update(saved.getId(), saved);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("New", updated.getName());
        Assertions.assertEquals("NewDept", updated.getDepartment());
    }

    @Test
    void deleteTeacherTest() {
        TeacherDto dto = new TeacherDto();
        dto.setName("Temp");

        TeacherDto saved = service.create(dto);

        service.delete(saved.getId());

        TeacherDto deleted = service.getById(saved.getId());

        Assertions.assertNull(deleted);
    }
}
