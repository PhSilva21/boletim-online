package com.bandeira.school_report_online.controllers;

import com.bandeira.school_report_online.dtos.CreateSchoolReportDTO;
import com.bandeira.school_report_online.dtos.FindBySchoolReportDTO;
import com.bandeira.school_report_online.dtos.UpdateSchoolReport;
import com.bandeira.school_report_online.model.SchoolReport;
import com.bandeira.school_report_online.services.SchoolReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/schoolReport")
public class SchoolReportController {


    @Autowired
    private SchoolReportService schoolReportService;

    @PostMapping("/createSchoolReport")
    public ResponseEntity<SchoolReport> createSchoolReport(@RequestBody CreateSchoolReportDTO createSchoolReportDTO){
        var response = schoolReportService.createSchoolReport(createSchoolReportDTO);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("findByStudent")
    public ResponseEntity<SchoolReport> findByStudent(@RequestParam @Param("Request")
                                                      FindBySchoolReportDTO request){
        var response = schoolReportService.findByStudent(request);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/updateSchoolReport")
    public ResponseEntity<SchoolReport> updateSchoolReport(@RequestBody UpdateSchoolReport updateSchoolReport) {
        var response = schoolReportService.updateSchoolReport(updateSchoolReport);
        return ResponseEntity.ok().body(response);
    }


}
