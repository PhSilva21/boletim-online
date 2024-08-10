package com.bandeira.school_report_online.controllers;

import com.bandeira.school_report_online.dtos.SchoolEnrollmentRequest;
import com.bandeira.school_report_online.dtos.UpdateSchoolEnrollment;
import com.bandeira.school_report_online.model.SchoolEnrollment;
import com.bandeira.school_report_online.services.SchoolEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schoolEnrollment")
public class SchoolEnrollmentController {


    @Autowired
    private static SchoolEnrollmentService schoolEnrollmentService;


    @PostMapping("/registerForSchool")
    public ResponseEntity<String> registerForSchool(SchoolEnrollmentRequest schoolEnrollmentRequest){
        var response = schoolEnrollmentService.registerForSchool(schoolEnrollmentRequest);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/findByProtocol")
    public ResponseEntity<SchoolEnrollment> findByProtocol(@RequestParam @Param("protocol") String protocol){
        var response = schoolEnrollmentService.findByProtocol(protocol);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/updateSchool")
    public ResponseEntity<Void> updateSchool(@RequestBody UpdateSchoolEnrollment updateSchoolEnrollment){
        schoolEnrollmentService.updateSchool(updateSchoolEnrollment);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/deleteById")
    public ResponseEntity<Void> deleteById(@RequestParam @Param("id") String id){
        schoolEnrollmentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
