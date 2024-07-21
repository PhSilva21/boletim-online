package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.StudentCreateRequest;
import com.bandeira.school_report_online.dtos.StudentCreateResponse;
import com.bandeira.school_report_online.exceptions.SchoolNotFound;
import com.bandeira.school_report_online.model.Student;
import com.bandeira.school_report_online.repositories.SchoolRepository;
import com.bandeira.school_report_online.repositories.StudentRepository;
import com.bandeira.school_report_online.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolRepository schoolRepository;


    public StudentCreateResponse createStudent(StudentCreateRequest studentCreateRequest){
        var school = schoolRepository.findByName(studentCreateRequest.schoolName());

        if(school == null) {
            throw new SchoolNotFound();
        }

            Student student = new Student(
                    UUID.randomUUID().toString(),
                    RandomString.generateRandomString(6),
                    studentCreateRequest.name(),
                    studentCreateRequest.county(),
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
}
