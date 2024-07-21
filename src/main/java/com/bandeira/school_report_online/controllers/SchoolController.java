package com.bandeira.school_report_online.controllers;

import com.bandeira.school_report_online.dtos.SchoolCreateRequest;
import com.bandeira.school_report_online.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
