package com.example.university.mapper;

import com.example.university.dto.StudentDto;
import com.example.university.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void convertEntityToDtoTest() {

        Student student = new Student();
        student.setId(1L);
        student.setName("Ali");
        student.setEmail("ali@mail.com");

        StudentDto dto = studentMapper.toDto(student);

        Assertions.assertNotNull(dto);

        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getName());
        Assertions.assertNotNull(dto.getEmail());

        Assertions.assertEquals(student.getId(), dto.getId());
        Assertions.assertEquals(student.getName(), dto.getName());
        Assertions.assertEquals(student.getEmail(), dto.getEmail());
    }

    @Test
    void convertDtoToEntityTest() {

        StudentDto dto = new StudentDto();
        dto.setId(2L);
        dto.setName("Amina");
        dto.setEmail("amina@mail.com");

        Student student = studentMapper.toEntity(dto);

        Assertions.assertNotNull(student);

        Assertions.assertNotNull(student.getId());
        Assertions.assertNotNull(student.getName());
        Assertions.assertNotNull(student.getEmail());

        Assertions.assertEquals(dto.getId(), student.getId());
        Assertions.assertEquals(dto.getName(), student.getName());
        Assertions.assertEquals(dto.getEmail(), student.getEmail());
    }

    @Test
    void convertEntityListToDtoListTest() {

        List<Student> students = new ArrayList<>();

        Student s1 = new Student();
        s1.setId(1L);
        s1.setName("Ali");
        s1.setEmail("ali@mail.com");

        Student s2 = new Student();
        s2.setId(2L);
        s2.setName("Amina");
        s2.setEmail("amina@mail.com");

        students.add(s1);
        students.add(s2);

        List<StudentDto> dtoList = studentMapper.toDtoList(students);

        Assertions.assertNotNull(dtoList);
        Assertions.assertNotEquals(0, dtoList.size());
        Assertions.assertEquals(students.size(), dtoList.size());

        for (int i = 0; i < dtoList.size(); i++) {
            Assertions.assertNotNull(dtoList.get(i));
        }
    }
}
