package com.system.management.controller;


import com.system.management.dto.CourseDTO;
import com.system.management.dto.StudentDTO;
import com.system.management.entity.Course;
import com.system.management.entity.Student;
import com.system.management.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    @Mock
    StudentService studentServiceMock;

    @InjectMocks
    StudentController studentController;

    @Test
    void findStudents() {
        //given
        List<Student> expected =new ArrayList<>();
        List<Student> expected2=new ArrayList<>();
        when(studentServiceMock.findAll()).thenReturn(expected);
        when(studentServiceMock.findStudentsByNameContaining(anyString())).thenReturn(expected2);
        //when
        List<Student> actual=this.studentController.findStudents("").getBody();
        List<Student> actual2=this.studentController.findStudents("n").getBody();
        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> assertNotNull(actual2),
                () -> assertEquals(expected2, actual2)

        );
    }

    @Test
    void findStudentsById() {
        //given
        Student expected=new Student();
        expected.setID(1);
        when(studentServiceMock.findById(anyInt())).thenReturn(expected);
        //when
        Student actual=this.studentController.findStudentsById(1).getBody();
        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                ()-> assertEquals(expected.getID(),actual.getID())

        );
    }

    @Test
    void saveStudent() {
        // given
        Student expected = new Student();
        expected.setID(101);
        when(studentServiceMock.save(any())).thenReturn(expected);

        // when
        StudentDTO dto = new StudentDTO();
        Student actual = this.studentController.saveStudent(dto).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getID(), actual.getID())
        );
    }

    @Test
    void updateStudent() {
        // given
        Student expected = new Student();
        expected.setID(101);
        when(studentServiceMock.update(any())).thenReturn(expected);

        // when
        StudentDTO dto = new StudentDTO();
        Student actual = this.studentController.updateStudent(dto).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getID(), actual.getID())
        );
    }

    @Test
    void deleteStudentById() {
        // given
        String expected = "Deleted...";
        // when
        String actual = this.studentController.deleteStudentById(1).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)

        );
    }

    @Test
    void deleteStudent() {
        // given
        String expected = "Deleted...";
        // when
        Student student=new Student();
        String actual = this.studentController.deleteStudent(student).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)

        );
    }

    /*@Test
    void getGendersWithGrouping() {
        //given
        List<?> expected =new ArrayList<>();
        when(studentServiceMock.getGendersWithGrouping()).thenReturn(expected);

        //when
        List<?> actual=this.studentController.getGendersWithGrouping();
        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }*/

    @Test
    void deleteStudentByName() {
        // given
        String expected = "Deleted...";


        // when
        String actual = this.studentController.deleteStudentByName("name").getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)

        );

    }
}