


package com.system.management.service;



import com.system.management.dto.CourseDTO;
import com.system.management.entity.Course;
import com.system.management.entity.Instructor;
import com.system.management.entity.Student;
import com.system.management.exceptions.CourseAlreadyExistsException;
import com.system.management.mappers.CourseMapper;
import com.system.management.repository.CourseRepository;
import com.system.management.repository.InstructorRepository;
import com.system.management.repository.StudentRepository;
import com.system.management.service.BaseService;
import com.system.management.util.CourseValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService implements BaseService<Course> {
    @Autowired
    private  CourseRepository repository;
    @Autowired
    private  CourseMapper mapper;
    @Autowired
    private  InstructorRepository instructorRepository;
    @Autowired
    private  StudentRepository studentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        Iterable<Course> courseIter=repository.findAll();
        List<Course> courseList=new ArrayList<>();
        courseIter.iterator().forEachRemaining(courseList::add);
        return courseList;
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(int id) {
        Optional<Course> course=repository.findById(id);

        return course.orElse(null);
    }
    @Transactional(readOnly = true)
    public List<Course> findByCourseNameContaining(String name){
        return repository.findByCourseNameContaining(name);
    }


    @Transactional
    public Course save(CourseDTO object) {
        if(repository.existsCourseByPrefix(object.getPrefix())){
            throw new CourseAlreadyExistsException("Course already exists with this prefix");
        }
        //CourseValidatorUtil.StudentNumberofCourseValidation(object);
        Course course=mapper.mapFromCourseDTOtoCourse(object);
        return repository.save(course);
    }


    @Transactional
    public Course update(CourseDTO object) {
        //CourseValidatorUtil.StudentNumberofCourseValidation(object);
        if(repository.existsCourseByPrefix(object.getPrefix())){
            throw new CourseAlreadyExistsException("Course already exists with this prefix");
        }
        Course course=mapper.mapFromCourseDTOtoCourse(object);
        return repository.save(course);
    }

    @Override
    @Transactional
    public void delete(Course object) {
        repository.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteByName(String name){
        repository.deleteByCourseName(name);
    }

    @Transactional(readOnly = true)
    public Instructor findInstructorById( int id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        return instructor.orElse(null);
    }
    @Transactional(readOnly = true)
    public List<Student> findStudentsById(List<Integer> students){
        List<Student> studentList=new ArrayList<>();
        for (int id:students
             ) {
            studentList.add(studentRepository.findById(id).orElse(null));
        }
        return studentList;
    }
    public List<Integer> getStudentsId(List<Student> studentList) {
        List<Integer> studentsId=new ArrayList<>();
        for (Student s : studentList) {
            studentsId.add(s.getID());
        }

        return studentsId;
    }

}
