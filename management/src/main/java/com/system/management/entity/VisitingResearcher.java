package com.system.management.entity;



import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;


@Entity
@Data
@NoArgsConstructor
public class VisitingResearcher extends Instructor {
    @NonNull
    private  double hourlySalary;

    @Builder
    public VisitingResearcher(String name,String address,long phoneNumber,double hourlySalary ){
        super(name, address, phoneNumber);
        this.hourlySalary=hourlySalary;
    }

}
