package com.system.management.service;






import com.system.management.dto.StudentDTO;
import com.system.management.entity.Course;
import com.system.management.entity.Student;
import com.system.management.mappers.StudentMapper;
import com.system.management.repository.CourseRepository;
import com.system.management.repository.StudentRepository;
import com.system.management.util.StudentValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StudentService implements BaseService<Student> {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private  StudentMapper mapper;
    @Autowired
    private CourseRepository courseRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        List<Student>studentList=new ArrayList<>();
        Iterable<Student>studentIter=studentRepository.findAll();
        studentIter.iterator().forEachRemaining(studentList::add);
        return  studentList;
    }

    @Override
    @Transactional(readOnly = true)
    public Student findById(int id) {
        Optional<Student>student=studentRepository.findById(id);
        return student.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<?> getGendersWithGrouping(){
        return studentRepository.getGendersWithGrouping();
    }

    @Transactional(readOnly = true)
    public List<Student> findStudentsByNameContaining(String name){
        return studentRepository.findByNameContaining(name);
    }


    @Transactional
    public Student save(StudentDTO object) {
        validateRequest(object);
        Student student=mapper.mapFromStudentDTOtoStudent(object);
        return studentRepository.save(student);
    }

    private void validateRequest(StudentDTO studentDTO) {
        StudentValidatorUtil.validateStudentAge(studentDTO.getBirthDate());
    }

    @Transactional
    public Student update(StudentDTO object) {
        validateRequest(object);
        Student student=mapper.mapFromStudentDTOtoStudent(object);
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void delete(Student object) {
        studentRepository.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public void deleteByName(String name){
        studentRepository.deleteByName(name);
    }

    @Transactional(readOnly = true)
    public List<Course> findCoursesById(List<Integer> courseIds) {
        List<Course> courseList=new ArrayList<>();
        if(courseIds.size()!=0) {
            for (int id : courseIds
            ) {
                courseList.add(courseRepository.findById(id).orElse(null));
            }
        }
        return courseList;
    }

    @Transactional(readOnly = true)
    public List<Integer> findCoursesId(List<Course> courseList) {
        List<Integer> courseIds=new ArrayList<>();
        if(courseList.size()!=0){
            for (Course c:courseList) {
                courseIds.add(c.getID());
            }
        }
        return courseIds;
    }
}


