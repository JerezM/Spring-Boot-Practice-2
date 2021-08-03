package com.jerezm.springsecuritypractice.student;

import java.util.List;

import com.jerezm.springsecuritypractice.exception.StudentNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    
    private static final List<Student> STUDENTS = List.of(
        new Student(1, "Facundo Conte"),
        new Student(2, "Luciano De Cecco"),
        new Student(3, "Sebastian Sole")
    );

    @GetMapping(path = "{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") Integer studentId) throws StudentNotFoundException {
        ResponseEntity<Student> responseEntity;

        Student obtainStudent = 
        STUDENTS.stream()
        .filter(student -> studentId.equals(student.getStudentId()))
        .findFirst()
        .orElseThrow(() -> new StudentNotFoundException("Student with id: "+ studentId +" not found"));

        responseEntity = ResponseEntity.ok().body(obtainStudent);
        

        return responseEntity;
    }

}
