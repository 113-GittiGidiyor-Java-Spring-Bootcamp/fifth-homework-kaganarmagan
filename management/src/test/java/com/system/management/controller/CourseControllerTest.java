package com.system.management.controller;

import com.system.management.dto.CourseDTO;
import com.system.management.entity.Course;
import com.system.management.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock
    CourseService courseServiceMock;

    @InjectMocks
    CourseController courseController;

    @Test
    void findCourses() {
        //given
        List<Course> expected =new ArrayList<>();
        List<Course> expected2=new ArrayList<>();
        when(courseServiceMock.findAll()).thenReturn(expected);
        when(courseServiceMock.findByCourseNameContaining(anyString())).thenReturn(expected2);
        //when
        List<Course> actual=this.courseController.findCourses("").getBody();
        List<Course> actual2=this.courseController.findCourses("n").getBody();
        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> assertNotNull(actual2),
                () -> assertEquals(expected2, actual2)

        );

    }

    @Test
    void findCoursesById() {
        //given
        Course expected=new Course();
        expected.setID(1);
        when(courseServiceMock.findById(anyInt())).thenReturn(expected);
        //when
        Course actual=this.courseController.findCoursesById(1).getBody();
        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                ()-> assertEquals(expected.getID(),actual.getID())

        );

    }

    @Test
    void saveCourse() {
        // given
        Course expected = new Course();
        expected.setID(101);
        when(courseServiceMock.save(any())).thenReturn(expected);

        // when
        CourseDTO dto = new CourseDTO();
        Course actual = this.courseController.saveCourse(dto).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getID(), actual.getID())
        );
    }

    @Test
    void updateCourse() {
        // given
        Course expected = new Course();
        expected.setID(101);
        when(courseServiceMock.update(any())).thenReturn(expected);

        // when
        CourseDTO dto = new CourseDTO();
        Course actual = this.courseController.updateCourse(dto).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getID(), actual.getID())
        );
    }

    @Test
    void deleteCourseById() {
        // given
        String expected = "Deleted...";
        // when
        String actual = this.courseController.deleteCourseById(1).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)

        );
    }

    @Test
    void deleteCourse() {
        // given
        String expected = "Deleted...";


        // when
        Course course = new Course();
        String actual = this.courseController.deleteCourse(course).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)

        );
    }

    @Test
    void deleteCourseByName() {
        // given
        String expected = "Deleted...";


        // when
        String actual = this.courseController.deleteCourseByName("").getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)

        );
    }
}