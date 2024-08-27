package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.SchoolEnrollmentRequest;
import com.bandeira.school_report_online.dtos.UpdateSchoolEnrollment;
import com.bandeira.school_report_online.exceptions.SchoolEnrollmentNotFound;
import com.bandeira.school_report_online.exceptions.SchoolNotFound;
import com.bandeira.school_report_online.exceptions.SchoolWithoutVacancies;
import com.bandeira.school_report_online.exceptions.StudentNotFound;
import com.bandeira.school_report_online.model.School;
import com.bandeira.school_report_online.model.SchoolEnrollment;
import com.bandeira.school_report_online.model.StatusSchoolEnrollment;
import com.bandeira.school_report_online.repositories.SchoolEnrollmentRepository;
import com.bandeira.school_report_online.repositories.SchoolRepository;
import com.bandeira.school_report_online.repositories.StudentRepository;
import com.bandeira.school_report_online.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class SchoolEnrollmentService {


    @Autowired
    private SchoolEnrollmentRepository schoolEnrollmentRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private StudentRepository studentRepository;


    public String registerForSchool(SchoolEnrollmentRequest enrollmentRequest) {
        var school = schoolRepository.findByName(enrollmentRequest.nameSchool());

        if (school == null) {
            throw new SchoolNotFound();
        }

        var student = studentRepository.findByStudentRegistration(enrollmentRequest.studentRegistration());

        if (student == null) {
            throw new StudentNotFound();
        }

        if (school.getVacancies() <= school.getStudents().size()) {
            throw new SchoolWithoutVacancies();
        }

        SchoolEnrollment schoolEnrollment = new SchoolEnrollment(
                UUID.randomUUID().toString(),
                student,
                school,
                RandomString.generateRandomString(8).toUpperCase(),
                StatusSchoolEnrollment.SENT);

        schoolEnrollmentRepository.save(schoolEnrollment);


        return "The school registration request has been made,"
                + " follow The progress via The registered email";
    }


    public SchoolEnrollment findByProtocol(String number) {
        var schoolEnrollment = schoolEnrollmentRepository.findByProtocol(number);

        if (schoolEnrollment == null) {
            throw new SchoolEnrollmentNotFound();
        }
        return schoolEnrollment;
    }


    public void updateSchool(UpdateSchoolEnrollment updateSchoolEnrollment) {
        var schoolEnrollment = schoolEnrollmentRepository.findByProtocol(updateSchoolEnrollment.protocol());

        if(schoolEnrollment == null) {
            throw new SchoolEnrollmentNotFound();
        }

        var school = schoolRepository.findByName(updateSchoolEnrollment.nameSchool());

        if(school == null) {
            throw new SchoolNotFound();
        }

        schoolEnrollment.setSchool(school);

        schoolEnrollmentRepository.save(schoolEnrollment);
    }



    public void deleteById(String id) {

        var obj = schoolEnrollmentRepository.findById(id).orElseThrow(SchoolEnrollmentNotFound::new);

        schoolEnrollmentRepository.deleteById(id);

    }
}


