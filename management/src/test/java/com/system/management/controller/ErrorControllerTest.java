package com.system.management.controller;

import com.system.management.entity.ErrorResponse;
import com.system.management.service.ErrorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ErrorControllerTest {

    @Mock
    ErrorService errorServiceMock;

    @InjectMocks
    ErrorController errorController;

    @Test
    void findCourses() {
        //given
        List<ErrorResponse> expected=new ArrayList<>();
        when(errorServiceMock.findAll()).thenReturn(expected);
        //when
        List<ErrorResponse> actual=errorController.findCourses().getBody();
        //then
        assertAll(
                ()-> assertEquals(expected,actual),
                ()-> assertNotNull(actual)

        );

    }
}