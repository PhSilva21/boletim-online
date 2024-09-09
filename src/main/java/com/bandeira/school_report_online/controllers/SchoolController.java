package com.bandeira.school_report_online.controllers;

import com.bandeira.school_report_online.dtos.EnrollStudent;
import com.bandeira.school_report_online.dtos.SchoolCreateRequest;
import com.bandeira.school_report_online.dtos.SchoolUpdateDTO;
import com.bandeira.school_report_online.model.School;
import com.bandeira.school_report_online.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("school")
public class SchoolController {


    @Autowired
    private SchoolService schoolService;


    @PostMapping
    public ResponseEntity<SchoolCreateRequest> createSchool(@RequestBody SchoolCreateRequest school){
        var response = schoolService.createSchool(school);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/enrollStudent")
    public ResponseEntity<String> enrollStudent(@RequestBody EnrollStudent enrollStudent) {
        schoolService.enrollStudent(enrollStudent);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/updateSchool")
    public ResponseEntity<Void> updateSchool(@RequestBody SchoolUpdateDTO schoolUpdateDTO) {
        schoolService.updateSchool(schoolUpdateDTO);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/findByCounty")
    public ResponseEntity<List<School>> findByCounty(@RequestParam @Param("county") String county){
        var response = schoolService.findByCounty(county);
        return ResponseEntity.ok().body(response);
    }

}
