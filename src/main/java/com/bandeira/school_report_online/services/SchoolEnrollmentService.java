package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.SchoolEnrollmentRequest;
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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.UUID;

public class SchoolEnrollmentService {

    @Autowired
    private SchoolEnrollmentRepository schoolEnrollmentRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JavaMailSender emailSender;


    public String registerForSchool(SchoolEnrollmentRequest enrollmentRequest) {
        var school = schoolRepository.findByName(enrollmentRequest.nameSchool());

        if (school == null) {
            throw new SchoolNotFound();
        }

        var student = studentRepository.findByName(enrollmentRequest.nameStudent());

        if (student == null) {
            throw new StudentNotFound();
        }

        if (school.getVacancies() <= school.getStudents().size()) {
            throw new SchoolWithoutVacancies();
        }

        var randomString = RandomString.generateRandomString(8).toUpperCase();

        SchoolEnrollment schoolEnrollment = new SchoolEnrollment(
                UUID.randomUUID().toString(),
                student,
                school,
                randomString.toUpperCase(),
                StatusSchoolEnrollment.SENT);

        schoolEnrollmentRepository.save(schoolEnrollment);


        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("pedro.amp002@gmail.com");
        message.setTo("user.getEmail()");
        message.setSubject("SchoolEnrollment...");
        message.setText("Sua inscrição...");


        emailSender.send(message);

        return "The school registration request has been made,"
                + " follow The progress via The registered email";
    }


    public SchoolEnrollment findByProtocol(String number) {
        var schoolEnrollment = schoolEnrollmentRepository.findByProtocol(number);

        if (number == null) {
            throw new SchoolEnrollmentNotFound();
        }
        return schoolEnrollment;
    }


    public void updateSchool(String number, School school) {
        var schoolEnrollment = schoolEnrollmentRepository.findByProtocol(number);

        if(number == null) {
            throw new SchoolEnrollmentNotFound();
        }

        schoolRepository.findByName(school.getName());

        if(school == null) {
            throw new SchoolNotFound();
        }

        schoolEnrollment.setSchool(school);
    }

    public void deleteById(String id) {

        var obj = schoolEnrollmentRepository.findById(id).orElseThrow(SchoolEnrollmentNotFound::new);

        schoolEnrollmentRepository.deleteById(id);

    }
}


