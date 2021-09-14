package com.system.management.repository;



import com.system.management.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course,Integer> {
    List<Course> findByCourseNameContaining(String name);
    void deleteByCourseName(String name);
    boolean existsCourseByPrefix(String prefix);
}
