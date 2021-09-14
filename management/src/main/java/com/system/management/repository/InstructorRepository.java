package com.system.management.repository;


import com.system.management.entity.Instructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor,Integer> {
    public boolean existsInstructorByPhoneNumber(long number);
}
