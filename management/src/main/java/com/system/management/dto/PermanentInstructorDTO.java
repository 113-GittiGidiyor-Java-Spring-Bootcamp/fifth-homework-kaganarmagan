package com.system.management.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermanentInstructorDTO {
    @ApiModelProperty(hidden=true)
    private int ID;
    private  String name;
    private  String address;
    private  long phoneNumber;
    private List<CourseDTO> courseList ;
    private double fixedSalary;

}
