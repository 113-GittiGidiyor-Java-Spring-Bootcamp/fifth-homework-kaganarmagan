package com.system.management.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends AbstractBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated
    private int ID;
    @NonNull
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    @NonNull
    private Gender gender;
    @NonNull
    private String name;
    @NonNull
    private String address;
    @JsonManagedReference
    @ManyToMany(mappedBy = "students")
    private List<Course> courseList=new ArrayList<>();

}
