package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.*;
import com.bandeira.school_report_online.exceptions.CountyNotFound;
import com.bandeira.school_report_online.exceptions.SchoolNotFound;
import com.bandeira.school_report_online.exceptions.StudentNotFound;
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
                    studentCreateRequest.cpf(),
                    studentCreateRequest.responsible(),
                    studentCreateRequest.celResponsible(),
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


    public List<Student> findByCounty(String countyName) {
        var county = countyRepository.findByName(countyName);

        if(county == null){
            throw new CountyNotFound();
        }

        return studentRepository.findAll().stream()
                .filter(s -> s.getCounty().getName().equals(county.getName())).collect(Collectors.toList());
    }


    public List<Student> findBySchool(String schoolName) {
        var school = schoolRepository.findByName(schoolName);

        if(school == null){
            throw new CountyNotFound();
        }

        return studentRepository.findAll().stream()
                .filter(s -> s.getSchool().getName().equals(school.getName())).collect(Collectors.toList());
    }


    public Student findById(String id){
        return studentRepository.findById(id).orElseThrow(StudentNotFound::new);
    }


    public void updateResponsible(UpdateResponsibleStudent updateResponsibleStudent){

        var student = studentRepository.findById(updateResponsibleStudent.id()).orElseThrow(StudentNotFound::new);

        student.setResponsible(updateResponsibleStudent.nameResponsible());
        student.setCelResponsible(updateResponsibleStudent.cel());

        studentRepository.save(student);
    }


    public void updateCounty(UpdateStudentCounty updateStudentCounty){

        var student = studentRepository.findById(updateStudentCounty.id()).orElseThrow(StudentNotFound::new);

        var county = countyRepository.findByName(updateStudentCounty.nameCounty());

        if(county == null){
            throw new CountyNotFound();
        }

        student.setCounty(county);

        studentRepository.save(student);
    }


    public void updateSchool(UpdateStudentSchool updateStudentSchool){

        var student = studentRepository.findById(updateStudentSchool.id()).orElseThrow(StudentNotFound::new);

        var county = countyRepository.findByName(updateStudentSchool.nameSchool());

        if(county == null){
            throw new CountyNotFound();
        }

        student.setCounty(county);

        studentRepository.save(student);
    }

}
