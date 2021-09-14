package com.system.management.util;

import com.system.management.dto.CourseDTO;

import com.system.management.exceptions.StudentNumberForOneCourseExceededException;

import java.util.Optional;

public class CourseValidatorUtil {
    public static void StudentNumberofCourseValidation(CourseDTO object) {

        if(object.getStudentsId().size()>19){
            throw new StudentNumberForOneCourseExceededException("Maximum 20 student can be registered for a course");
        }
    }
}
