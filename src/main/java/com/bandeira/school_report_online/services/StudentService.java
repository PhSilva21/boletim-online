package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.StudentCreateRequest;
import com.bandeira.school_report_online.dtos.StudentCreateResponse;
import com.bandeira.school_report_online.exceptions.CountyNotFound;
import com.bandeira.school_report_online.exceptions.SchoolNotFound;
import com.bandeira.school_report_online.model.Student;
import com.bandeira.school_report_online.repositories.CountyRepository;
import com.bandeira.school_report_online.repositories.SchoolRepository;
import com.bandeira.school_report_online.repositories.StudentRepository;
import com.bandeira.school_report_online.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private CountyRepository countyRepository;


    public StudentCreateResponse createStudent(StudentCreateRequest studentCreateRequest){
        var school = schoolRepository.findByName(studentCreateRequest.schoolName());

        var county = countyRepository.findByName(studentCreateRequest.countyName());

        if(school == null) {
            throw new SchoolNotFound();
        }
        if(county == null){
            throw new CountyNotFound();
        }

            Student student = new Student(
                    UUID.randomUUID().toString(),
                    RandomString.generateRandomString(6),
                    studentCreateRequest.name(),
                    county,
                    school
            );

        studentRepository.save(student);

        return new StudentCreateResponse(
                student.getName(),
                student.getStudentRegistration(),
                student.getCounty(),
                student.getSchool()
        );
    }


