package com.bandeira.school_report_online.controllers;

import com.bandeira.school_report_online.dtos.StudentCreateRequest;
import com.bandeira.school_report_online.dtos.StudentCreateResponse;
import com.bandeira.school_report_online.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {


    @Autowired
    private StudentService studentService;


    @PostMapping
    public ResponseEntity<StudentCreateResponse> createStudent(StudentCreateRequest student){
        var response = studentService.createStudent(student);
        return ResponseEntity.ok().body(response);
    }
}
