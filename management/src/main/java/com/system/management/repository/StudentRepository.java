package com.system.management.repository;



import com.system.management.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    List<Student> findByNameContaining(String name);
    void deleteByName(String name);

    @Query("select s.gender,count(s.gender) from Student s group by s.gender")
    List<?>getGendersWithGrouping();
}
