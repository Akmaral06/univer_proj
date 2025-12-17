package com.example.university.service;

import com.example.university.UniversityApplication;
import com.example.university.dto.StudentDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = UniversityApplication.class)
class StudentServiceTest {

    @Autowired
    private StudentService service;

    @Test
    void createStudentTest() {
        StudentDto dto = new StudentDto();
        dto.setName("Aruzhan");
        dto.setEmail("a@mail.com");

        StudentDto saved = service.create(dto);

        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("Aruzhan", saved.getName());
        Assertions.assertEquals("a@mail.com", saved.getEmail());
    }

    @Test
    void getAllTest() {
        StudentDto dto = new StudentDto();
        dto.setName("Ali");
        dto.setEmail("ali@mail.com");

        service.create(dto);

        List<StudentDto> list = service.getAll();

        Assertions.assertNotNull(list);
        Assertions.assertNotEquals(0, list.size());

        for (StudentDto student : list) {
            Assertions.assertNotNull(student);
            Assertions.assertNotNull(student.getId());
            Assertions.assertNotNull(student.getName());
        }
    }

    @Test
    void getByIdTest() {
        StudentDto dto = new StudentDto();
        dto.setName("Dana");
        dto.setEmail("d@mail.com");

        StudentDto saved = service.create(dto);

        StudentDto found = service.getById(saved.getId());

        Assertions.assertNotNull(found);
        Assertions.assertEquals(saved.getId(), found.getId());
        Assertions.assertEquals(saved.getName(), found.getName());
    }

    @Test
    void deleteStudentTest() {
        StudentDto dto = new StudentDto();
        dto.setName("Temp");

        StudentDto saved = service.create(dto);

        service.delete(saved.getId());

        StudentDto deleted = service.getById(saved.getId());

        Assertions.assertNull(deleted);
    }
}
