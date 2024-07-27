package com.bandeira.school_report_online.controllers;

import com.bandeira.school_report_online.dtos.CountyCreateRequest;
import com.bandeira.school_report_online.services.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("county")
public class CountyController {

    @Autowired
    private CountyService countyService;


    @PostMapping
    public ResponseEntity<CountyCreateRequest> createCounty(@RequestBody CountyCreateRequest countyCreateRequest){
        var response = countyService.createCounty(countyCreateRequest);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/deleteById")
    public ResponseEntity<Void> deleteById(@RequestParam @Param("id") String id){
        countyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
