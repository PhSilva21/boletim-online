package com.bandeira.school_report_online.controllers;

import com.bandeira.school_report_online.dtos.FindBySchoolReportDTO;
import com.bandeira.school_report_online.model.SchoolReport;
import com.bandeira.school_report_online.services.SchoolReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schoolReport")
public class SchoolReportController {


    @Autowired
    private SchoolReportService schoolReportService;


    @GetMapping("findByStudent")
    public ResponseEntity<SchoolReport> findByStudent(@RequestParam @Param("Request")
                                                      FindBySchoolReportDTO request){
        var response = schoolReportService.findByStudent(request);
        return ResponseEntity.ok().body(response);
    }

}
