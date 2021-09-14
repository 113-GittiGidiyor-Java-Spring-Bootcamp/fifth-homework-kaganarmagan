package com.system.management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated
    private int ID;
    @NonNull
    private String courseName;
    @NonNull
    private String prefix;
    @NonNull
    private int creditScore;

    @JsonBackReference
    @ManyToMany
    private List<Student> students=new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    private Instructor instructor;



}
