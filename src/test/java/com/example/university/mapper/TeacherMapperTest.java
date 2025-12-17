package com.example.university.mapper;

import com.example.university.dto.TeacherDto;
import com.example.university.entity.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TeacherMapperTest {

    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    void convertEntityToDtoTest() {

        Teacher teacher = new Teacher(1L, "Ali", "IT");
        TeacherDto dto = teacherMapper.toDto(teacher);

        Assertions.assertNotNull(dto);

        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getName());
        Assertions.assertNotNull(dto.getDepartment());

        Assertions.assertEquals(teacher.getId(), dto.getId());
        Assertions.assertEquals(teacher.getName(), dto.getName());
        Assertions.assertEquals(teacher.getDepartment(), dto.getDepartment());
    }

    @Test
    void convertDtoToEntityTest() {

        TeacherDto dto = new TeacherDto();
        dto.setId(2L);
        dto.setName("Aigerim");
        dto.setDepartment("Math");

        Teacher teacher = teacherMapper.toEntity(dto);

        Assertions.assertNotNull(teacher);

        Assertions.assertNotNull(teacher.getId());
        Assertions.assertNotNull(teacher.getName());
        Assertions.assertNotNull(teacher.getDepartment());

        Assertions.assertEquals(dto.getId(), teacher.getId());
        Assertions.assertEquals(dto.getName(), teacher.getName());
        Assertions.assertEquals(dto.getDepartment(), teacher.getDepartment());
    }

    @Test
    void convertListEntityToDtoListTest() {

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1L, "Ali", "IT"));
        teachers.add(new Teacher(2L, "Dana", "Physics"));
        teachers.add(new Teacher(3L, "Murat", "History"));

        List<TeacherDto> dtoList = teacherMapper.toDtoList(teachers);

        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(teachers.size(), dtoList.size());

        for (int i = 0; i < dtoList.size(); i++) {
            Assertions.assertNotNull(dtoList.get(i));
        }
    }
}
