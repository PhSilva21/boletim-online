package com.bandeira.school_report_online.infra;

import com.bandeira.school_report_online.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(CountyNotFound.class)
    private ResponseEntity<String> countyNotFound(CountyNotFound exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("County not found.");
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    private ResponseEntity<String> emailAlreadyExist(CountyNotFound exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("This email is already registered to another account, log in.");
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    private ResponseEntity<String> incorrectPassword(IncorrectPasswordException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Incorrect password.");
    }

    @ExceptionHandler(PasswordsDoNotMatch.class)
    private ResponseEntity<String> passwordDoNotMatch(PasswordsDoNotMatch exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Password do no match.");
    }

    @ExceptionHandler(SchoolEnrollmentNotFound.class)
    private ResponseEntity<String> schoolEnrollmentNotFound(SchoolEnrollmentNotFound exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("School enrollment not found.");
    }

    @ExceptionHandler(SchoolNotFound.class)
    private ResponseEntity<String> schoolNotFound(SchoolNotFound exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("School no found.");
    }

    @ExceptionHandler(SchoolReportNotFoundException.class)
    private ResponseEntity<String> schoolReportNotFound(SchoolReportNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("School report not found.");
    }

    @ExceptionHandler(SchoolWithoutVacancies.class)
    private ResponseEntity<String> schoolWithoutVacancies(SchoolWithoutVacancies exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("The school currently has no vacancies");
    }

    @ExceptionHandler(StudentNotFound.class)
    private ResponseEntity<String> studentNotFound(StudentNotFound exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<String> userNotFound(UserNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
