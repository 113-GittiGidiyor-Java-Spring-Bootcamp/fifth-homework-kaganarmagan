package com.system.management.service;

import com.system.management.dto.CourseDTO;
import com.system.management.entity.Course;
import com.system.management.entity.ErrorResponse;
import com.system.management.repository.ErrorRepository;
import com.system.management.repository.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class ErrorServiceTest {
    @Mock
    ErrorRepository mockErrorRepository;

    @InjectMocks
    ErrorService errorService;
    @Test
    void findAll() {
        //given
        List<ErrorResponse> expected=new ArrayList<>();
        when(mockErrorRepository.findAll()).thenReturn(expected);
        //when
        List<ErrorResponse> actual=errorService.findAll();
        //then
        assertAll(
                ()-> assertEquals(actual,expected),
                ()-> assertNotNull(actual)

        );
    }

    @Test
    void save() {
        //given
        ErrorResponse expected=new ErrorResponse();
        when(mockErrorRepository.save(any())).thenReturn(expected);
        //when
        ErrorResponse errorResponse=new ErrorResponse();
        ErrorResponse actual=errorService.save(errorResponse);
        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()->assertEquals(actual,expected)
        );
    }
}