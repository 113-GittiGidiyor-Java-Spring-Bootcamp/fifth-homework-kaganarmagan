package com.system.management.entity;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class PermanentInstructor extends Instructor {
    @NonNull
    private double fixedSalary;

    @Builder
    public PermanentInstructor(String name,String address,long phoneNumber,double fixedSalary ){
        super(name, address, phoneNumber);
        this.fixedSalary=fixedSalary;
    }
}
