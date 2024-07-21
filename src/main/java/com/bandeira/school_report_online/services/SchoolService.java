package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.SchoolCreateRequest;
import com.bandeira.school_report_online.dtos.StudentCreateRequest;
import com.bandeira.school_report_online.model.School;
import com.bandeira.school_report_online.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;


    public SchoolCreateRequest createSchool(SchoolCreateRequest schoolCreateRequest){

        School school = new School(
                UUID.randomUUID().toString(),
                schoolCreateRequest.name(),
                schoolCreateRequest.county()
        );

        schoolRepository.save(school);

        return schoolCreateRequest;
    }
}
