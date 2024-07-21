package com.bandeira.school_report_online.controllers;

import com.bandeira.school_report_online.dtos.StudentCreateRequest;
import com.bandeira.school_report_online.dtos.StudentCreateResponse;
import com.bandeira.school_report_online.model.Student;
import com.bandeira.school_report_online.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {


    @Autowired
    private StudentService studentService;


    @PostMapping
    public ResponseEntity<StudentCreateResponse> createStudent(@RequestBody StudentCreateRequest student){
        var response = studentService.createStudent(student);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/findByCounty")
    public ResponseEntity<List<Student>> findByCounty(@RequestParam @Param("countyName") String countyName){
        var response = studentService.findByCounty(countyName);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/findBySchool")
    public ResponseEntity<List<Student>> findBySchool(@RequestParam ("schoolName") String schoolName){
        var response = studentService.findBySchool(schoolName);
        return ResponseEntity.ok().body(response);
    }
}
