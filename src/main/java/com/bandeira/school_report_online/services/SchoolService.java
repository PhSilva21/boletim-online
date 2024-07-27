package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.EnrollStudent;
import com.bandeira.school_report_online.dtos.SchoolCreateRequest;
import com.bandeira.school_report_online.dtos.SchoolUpdateDTO;
import com.bandeira.school_report_online.dtos.StudentCreateRequest;
import com.bandeira.school_report_online.exceptions.CountyNotFound;
import com.bandeira.school_report_online.exceptions.SchoolEnrollmentNotFound;
import com.bandeira.school_report_online.exceptions.SchoolNotFound;
import com.bandeira.school_report_online.exceptions.StudentNotFound;
import com.bandeira.school_report_online.model.School;
import com.bandeira.school_report_online.repositories.CountyRepository;
import com.bandeira.school_report_online.repositories.SchoolRepository;
import com.bandeira.school_report_online.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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


    public String enrollStudent(EnrollStudent enrollStudent){
        var student = studentRepository.findByStudentRegistration(enrollStudent.studentRegistration());

        if(student == null){
            throw new StudentNotFound();
        }

        var school = schoolRepository.findByName(enrollStudent.schoolName());

        if(school == null){
            throw new SchoolNotFound();
        }

        student.setSchool(school);

        school.getStudents().add(student);

        studentRepository.save(student);

        schoolRepository.save(school);

        return "Student successfully enrolled";
    }


    public void updateSchool(SchoolUpdateDTO schoolUpdateDTO) {
        var school = schoolRepository.findById(schoolUpdateDTO.id()).orElseThrow(SchoolNotFound::new);

        if (schoolUpdateDTO.name() != null) {
            school.setName(schoolUpdateDTO.name());
        }

        if (schoolUpdateDTO.nameCounty() != null) {
            school.setCounty(countyRepository.findByName(schoolUpdateDTO.name()));
        }
    }


    private void deleteById(String id){
        var school = schoolRepository.findById(id).orElseThrow(SchoolEnrollmentNotFound::new);

        schoolRepository.deleteById(id);
    }


    private School findById(String id) {
        return schoolRepository.findById(id).orElseThrow(SchoolEnrollmentNotFound::new);
    }


    public List<School> findByCounty(String countyName){
        var county = countyRepository.findByName(countyName);

        return county.getSchools().stream().filter(
                s -> s.getCounty().equals(county)).collect(Collectors.toList());
    }



    public School findByName(String name){
        return schoolRepository.findByName(name);
    }

}
