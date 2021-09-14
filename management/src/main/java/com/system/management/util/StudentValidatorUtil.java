package com.system.management.util;

import com.system.management.exceptions.StudentAgeNotValidException;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class StudentValidatorUtil {
    public static void validateStudentAge(LocalDate birthDate) {
        int age=Period.between(birthDate,LocalDate.now()).getYears();
        if(age<18||age>40){
            throw new StudentAgeNotValidException("Student's age cannot be exceeded 40 or under 18 ");
        }
    }
}
