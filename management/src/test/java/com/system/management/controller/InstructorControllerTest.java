package com.system.management.controller;

import com.system.management.dto.CourseDTO;
import com.system.management.dto.PermanentInstructorDTO;
import com.system.management.dto.VisitingResearcherDTO;
import com.system.management.entity.Course;
import com.system.management.entity.PermanentInstructor;
import com.system.management.entity.VisitingResearcher;
import com.system.management.service.PermanentInstructorService;
import com.system.management.service.VisitingResearcherService;
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
class InstructorControllerTest {
    @Mock
    PermanentInstructorService permanentInstructorServiceMock;
    @Mock
    VisitingResearcherService visitingResearcherServiceMock;

    @InjectMocks
    InstructorController instructorController;

    @Test
    void findPermanentInstructors() {
        //given
        List<PermanentInstructor> expected =new ArrayList<>();
        List<PermanentInstructor> expected2=new ArrayList<>();
        when(permanentInstructorServiceMock.findAll()).thenReturn(expected);
        when(permanentInstructorServiceMock.findByNameContaining(anyString())).thenReturn(expected2);
        //when
        List<PermanentInstructor> actual=this.instructorController.findPermanentInstructors("").getBody();
        List<PermanentInstructor> actual2=this.instructorController.findPermanentInstructors("n").getBody();
        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> assertNotNull(actual2),
                () -> assertEquals(expected2, actual2)

        );

    }

    @Test
    void findPermanentInstructorById() {
        //given
        PermanentInstructor expected=new PermanentInstructor();
        expected.setID(1);
        when(permanentInstructorServiceMock.findById(anyInt())).thenReturn(expected);
        //when
        PermanentInstructor actual=this.instructorController.findPermanentInstructorById(1).getBody();
        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                ()-> assertEquals(expected.getID(),actual.getID())

        );

    }

    @Test
    void savePermanentInstructor() {
        // given
        PermanentInstructor expected = new PermanentInstructor();
        expected.setID(101);
        when(permanentInstructorServiceMock.save(any())).thenReturn(expected);

        // when
        PermanentInstructorDTO dto = new PermanentInstructorDTO();
        PermanentInstructor actual = this.instructorController.savePermanentInstructor(dto).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getID(), actual.getID())
        );
    }

    @Test
    void updatePermanentInstructor() {
        // given
        PermanentInstructor expected = new PermanentInstructor();
        expected.setID(101);
        when(permanentInstructorServiceMock.update(any())).thenReturn(expected);

        // when
        PermanentInstructorDTO dto = new PermanentInstructorDTO();
        PermanentInstructor actual = this.instructorController.updatePermanentInstructor(dto).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getID(), actual.getID())
        );
    }

    @Test
    void deletePermanentInstructorById() {
        // given
        String expected = "Deleted...";
        // when
        String actual = this.instructorController.deletePermanentInstructorById(1).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)

        );
    }

    @Test
    void deletePermanentInstructor() {
        // given
        String expected = "Deleted...";
        // when
        PermanentInstructor instructor=new PermanentInstructor();
        String actual = this.instructorController.deletePermanentInstructor(instructor).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)

        );
    }

    @Test
    void deletePermanentInstructorByName() {
        // given
        String expected = "Deleted...";
        // when
        String actual = this.instructorController.deletePermanentInstructorByName("name").getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)

        );
    }

    @Test
    void changePermanentInstructorSalary() {
        //given
        PermanentInstructor expected=new PermanentInstructor();
        when(permanentInstructorServiceMock.existsById(anyInt())).thenReturn(true);
        when(permanentInstructorServiceMock.updateSalary(anyInt(),anyDouble())).thenReturn(expected);
        //when
        PermanentInstructor actual=instructorController.changePermanentInstructorSalary(1,0.1).getBody();
        //then
        assertAll(
                ()-> assertEquals(actual,expected),
                ()-> assertNotNull(actual)
        );
    }

    @Test
    void findVisitingResearchers() {
        //given
        List<VisitingResearcher> expected =new ArrayList<>();
        List<VisitingResearcher> expected2=new ArrayList<>();
        when(visitingResearcherServiceMock.findAll()).thenReturn(expected);
        when(visitingResearcherServiceMock.findByNameContaining(anyString())).thenReturn(expected2);
        //when
        List<VisitingResearcher> actual=this.instructorController.findVisitingResearchers("").getBody();
        List<VisitingResearcher> actual2=this.instructorController.findVisitingResearchers("n").getBody();
        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> assertNotNull(actual2),
                () -> assertEquals(expected2, actual2)

        );

    }

    @Test
    void findVisitingResearcherById() {
        //given
        VisitingResearcher expected=new VisitingResearcher();
        expected.setID(1);
        when(visitingResearcherServiceMock.findById(anyInt())).thenReturn(expected);
        //when
        VisitingResearcher actual=this.instructorController.findVisitingResearcherById(1).getBody();
        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                ()-> assertEquals(expected.getID(),actual.getID())

        );
    }

    @Test
    void saveVisitingResearcher() {
        // given
        VisitingResearcher expected = new VisitingResearcher();
        expected.setID(101);
        when(visitingResearcherServiceMock.save(any())).thenReturn(expected);

        // when
        VisitingResearcherDTO dto = new VisitingResearcherDTO();
        VisitingResearcher actual = this.instructorController.saveVisitingResearcher(dto).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getID(), actual.getID())
        );


    }

    @Test
    void updateVisitingResearcher() {
        // given
        VisitingResearcher expected = new VisitingResearcher();
        expected.setID(101);
        when(visitingResearcherServiceMock.update(any())).thenReturn(expected);

        // when
        VisitingResearcherDTO dto = new VisitingResearcherDTO();
        VisitingResearcher actual = this.instructorController.updateVisitingResearcher(dto).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getID(), actual.getID())
        );
    }

    @Test
    void deleteVisitingResearcherById() {
        // given
        String expected = "Deleted...";
        // when
        String actual = this.instructorController.deleteVisitingResearcherById(1).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)

        );
    }

    @Test
    void deleteVisitingResearcher() {
        // given
        String expected = "Deleted...";
        // when
        VisitingResearcher instructor=new VisitingResearcher();
        String actual = this.instructorController.deleteVisitingResearcher(instructor).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)

        );
    }

    @Test
    void deleteVisitingResearcherByName() {
        // given
        String expected = "Deleted...";
        // when
        String actual = this.instructorController.deleteVisitingResearcherByName("name").getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)

        );
    }

    @Test
    void changeVisitingResearcherSalary() {
        //given
        VisitingResearcher expected=new VisitingResearcher();
        when(visitingResearcherServiceMock.existsById(anyInt())).thenReturn(true);
        when(visitingResearcherServiceMock.updateSalary(anyInt(),anyDouble())).thenReturn(expected);
        //when
        VisitingResearcher actual=instructorController.changeVisitingResearcherSalary(1,0.1).getBody();
        //then
        assertAll(
                ()-> assertEquals(actual,expected),
                ()-> assertNotNull(actual)
        );
    }

    @Test
    void getTop3InstructorOrderBySalary() {
        //given
        List<PermanentInstructor> expected=new ArrayList<>();
        when(permanentInstructorServiceMock.findTop3ByOrderByFixedSalaryDesc()).thenReturn(expected);
        //when
        List<PermanentInstructor> actual=instructorController.getTop3InstructorOrderBySalary().getBody();
        //then
        assertAll(
                ()-> assertEquals(actual,expected),
                ()-> assertNotNull(actual)
        );
    }

    @Test
    void getBottom3InstructorOrderBySalary() {
        //given
        List<VisitingResearcher> expected=new ArrayList<>();
        when(visitingResearcherServiceMock.findBottom3InstructorOrderBySalary()).thenReturn(expected);
        //when
        List<VisitingResearcher> actual=instructorController.getBottom3InstructorOrderBySalary().getBody();
        //then
        assertAll(
                ()-> assertEquals(actual,expected),
                ()-> assertNotNull(actual)
        );
    }

    /*@Test TODO write method
    void findExchangeLoggers() {


    }*/
}