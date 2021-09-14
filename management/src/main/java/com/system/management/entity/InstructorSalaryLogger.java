package com.system.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorSalaryLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String clientIpAdress;
    private String clientUrl;
    private String sessionActivityId;
    private long instructorId;
    private double salaryAfter;
    private double salaryBefore;
    private double exchangeRatio;
    private LocalDate exchangeDateTime;
}
