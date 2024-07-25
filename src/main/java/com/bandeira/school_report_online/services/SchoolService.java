package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.EnrollStudent;
import com.bandeira.school_report_online.dtos.SchoolCreateRequest;
import com.bandeira.school_report_online.dtos.StudentCreateRequest;
import com.bandeira.school_report_online.exceptions.CountyNotFound;
import com.bandeira.school_report_online.exceptions.SchoolNotFound;
import com.bandeira.school_report_online.exceptions.StudentNotFound;
import com.bandeira.school_report_online.model.School;
import com.bandeira.school_report_online.repositories.CountyRepository;
import com.bandeira.school_report_online.repositories.SchoolRepository;
import com.bandeira.school_report_online.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private StudentRepository studentRepository;



    public SchoolCreateRequest createSchool(SchoolCreateRequest schoolCreateRequest){
        var county = countyRepository.findByName(schoolCreateRequest.countyName());

        if(county == null){
            throw new CountyNotFound();
        }

        School school = new School(
                UUID.randomUUID().toString(),
                schoolCreateRequest.name(),
                county
        );

        schoolRepository.save(school);

        return schoolCreateRequest;
    }

    
}
