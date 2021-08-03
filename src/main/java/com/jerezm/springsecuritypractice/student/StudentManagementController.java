package com.jerezm.springsecuritypractice.student;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
    
    private static final List<Student> STUDENTS = List.of(
        new Student(1, "Facundo Conte"),
        new Student(2, "Luciano De Cecco"),
        new Student(3, "Sebastian Sole")
    );

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        ResponseEntity<List<Student>> responseEntity = ResponseEntity.ok(STUDENTS);

        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<Student> registerNewStudent(RequestEntity<Student> requestEntity) {
        Student studentToRegister = requestEntity.getBody();

        System.out.println(studentToRegister+" was added succesfully");

        ResponseEntity<Student> responseEntity = 
            ResponseEntity.status(HttpStatus.CREATED).body(studentToRegister);

        return responseEntity;
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Integer studentId) {
        String msg = "the student with the id: "+studentId+" was deleted succesfully";

        System.out.println(msg);

        ResponseEntity<String> responseEntity = ResponseEntity.ok(msg);

        return responseEntity;
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable("studentId") Integer studentId,
                                                RequestEntity<Student> requestEntity) {

        Student studentToUpdate = requestEntity.getBody();

        String msg = "the student with the id: "+studentId+" was updated succesfully";

        System.out.println(msg);
                                            
        ResponseEntity<String> responseEntity = ResponseEntity.ok(msg);
                                            
        return responseEntity;

    }
}
