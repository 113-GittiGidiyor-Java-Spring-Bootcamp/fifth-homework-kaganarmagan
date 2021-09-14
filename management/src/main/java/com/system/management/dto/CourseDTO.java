package com.system.management.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    @ApiModelProperty(hidden=true)
    private int ID;
    private String courseName;
    private String prefix;
    private int creditScore;

    private List<Integer> studentsId;
    private Integer instructorId;


}
