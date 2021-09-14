package com.system.management.mappers;

import com.system.management.dto.CourseDTO;
import com.system.management.entity.Course;
import com.system.management.entity.Instructor;
import com.system.management.entity.Student;
import com.system.management.service.CourseService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;



@Mapper(componentModel = "spring")
public abstract class CourseMapper {
    @Autowired
    public CourseService courseService;

    @Mapping(target= "instructorId",source = "instructor.ID")
    @Mapping(target="studentsId",expression = "java(courseService.getStudentsId(course.getStudents()))")
    public abstract CourseDTO mapFromCourseToCourseDTO(Course course);

    @Mapping(target="students",expression = "java(courseService.findStudentsById(courseDTO.getStudentsId()))")
    @Mapping(target="instructor",expression = "java(courseService.findInstructorById(courseDTO.getInstructorId()))")
    public abstract Course mapFromCourseDTOtoCourse(CourseDTO courseDTO);


}
