package com.system.management.dto;

import com.system.management.entity.Course;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class StudentDTO {
    @ApiModelProperty(hidden=true)
    private int ID;
    private LocalDate birthDate;
    private GenderDTO gender;
    private String name;
    private String address;
    private List<CourseDTO> courseList;

}
