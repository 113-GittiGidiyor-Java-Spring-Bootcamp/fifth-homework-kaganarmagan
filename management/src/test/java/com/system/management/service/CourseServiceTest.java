package com.system.management.service;

import com.system.management.dto.CourseDTO;
import com.system.management.entity.Course;
import com.system.management.entity.Instructor;
import com.system.management.mappers.CourseMapper;
import com.system.management.repository.CourseRepository;
import com.system.management.repository.InstructorRepository;
import com.system.management.repository.StudentRepository;
import com.system.management.util.CourseValidatorUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class CourseServiceTest {
    @Mock
    CourseRepository mockCourseRepository;

    @Mock
    StudentRepository mockStudentRepository;

    @Mock
    InstructorRepository mockInstructorRepository;

    @Mock
    CourseMapper mockCourseMapper;

    @InjectMocks
    CourseService courseService;

    @Test
    void findAll() {//Todo :Not sure if it is ok. Make research about it!
        //given
        List<Course> expected=new ArrayList<>();
        when(mockCourseRepository.findAll()).thenReturn(expected);
        //when
        List<Course> actual=courseService.findAll();
        //then
        assertAll(
                ()-> assertEquals(actual,expected),
                ()-> assertNotNull(actual)

        );
    }

    @Test
    void findById() {
        //given
        Course course=new Course();
        course.setID(1);
        Optional<Course> expected =Optional.of(course);
        when(mockCourseRepository.findById(anyInt())).thenReturn(expected);
        //when
        Course actual=courseService.findById(1);
        //then
        assertAll(
                ()-> assertEquals(actual.getID(),expected.get().getID()),
                ()-> assertNotNull(actual),
                ()->assertEquals(actual,expected.get())
        );
    }

    @Test
    void findByCourseNameContaining() {
        //given
        List<Course> expected=new ArrayList<>();
        when(mockCourseRepository.findByCourseNameContaining(anyString())).thenReturn(expected);
        //when
        List<Course> actual=courseService.findByCourseNameContaining("name");
        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()->assertEquals(actual,expected)
        );

    }

    @Test
    void save() {
        //given
        //when(mockCourseRepository.existsCourseByPrefix(anyString())).thenReturn(true);
        when(mockCourseRepository.existsCourseByPrefix(any())).thenReturn(false);

        Course expected=new Course();
        when(mockCourseMapper.mapFromCourseDTOtoCourse(any())).thenReturn(expected);
        when(mockCourseRepository.save(any())).thenReturn(expected);
        //when
        CourseDTO courseDTO=new CourseDTO();
        Course actual=courseService.save(courseDTO);
        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()->assertEquals(actual,expected)
        );
    }

    @Test
    void update() {
        //given
        //when(mockCourseRepository.existsCourseByPrefix(anyString())).thenReturn(true);
        when(mockCourseRepository.existsCourseByPrefix(any())).thenReturn(false);

        Course expected=new Course();
        when(mockCourseMapper.mapFromCourseDTOtoCourse(any())).thenReturn(expected);
        when(mockCourseRepository.save(any())).thenReturn(expected);
        //when
        CourseDTO courseDTO=new CourseDTO();
        Course actual=courseService.save(courseDTO);
        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()->assertEquals(actual,expected)
        );
    }

    @Test
    void delete() {
        //no response
    }

    @Test
    void deleteById() {//no response
    }

    @Test
    void deleteByName() {//no response
    }

    @Test
    void findInstructorById() {
        //given
        Instructor expected=new Instructor();

        when(mockInstructorRepository.findById(anyInt())).thenReturn(Optional.of(expected));
        //when
        Instructor actual=courseService.findInstructorById(1);
        //then
        assertAll(
                ()-> assertEquals(actual,expected),
                ()-> assertNotNull(actual)

        );
    }

   /* @Test
    void findStudentsById() {
    }

    @Test
    void getStudentsId() {
    }*/
}