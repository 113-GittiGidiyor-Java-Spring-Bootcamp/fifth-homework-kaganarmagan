package com.system.management.controller;



import com.system.management.dto.StudentDTO;
import com.system.management.entity.Student;
import com.system.management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @GetMapping("/students")
    public ResponseEntity<List<Student>> findStudents(@RequestParam(required = false,defaultValue = "") String name) {
        if(name.length()!=0){
            return new ResponseEntity<>(studentService.findStudentsByNameContaining(name),HttpStatus.OK);

        }else {
            return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> findStudentsById(@PathVariable int id) {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentDTO student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @PutMapping("/students")
    public ResponseEntity<Student> updateStudent(@RequestBody StudentDTO student) {
        return new ResponseEntity<>(studentService.update(student), HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable int id) {
        studentService.deleteById(id);
        return new ResponseEntity<>("Deleted...", HttpStatus.OK);
    }

    @DeleteMapping("/students")
    public ResponseEntity<String> deleteStudent(@RequestBody Student student) {
        studentService.delete(student);
        return new ResponseEntity<>("Deleted...", HttpStatus.OK);
    }

    @GetMapping("/students/genders")
    public List<?> getGendersWithGrouping(){
        return studentService.getGendersWithGrouping();
    }

    @DeleteMapping("/students/name/{name}")
    public ResponseEntity<String> deleteStudentByName(@PathVariable String name){
        studentService.deleteByName(name);
        return new ResponseEntity<String>("Deleted...",HttpStatus.OK);
    }

}
