package com.bandeira.school_report_online.exceptions;

public class SchoolEnrollmentNotFound extends RuntimeException{


    public SchoolEnrollmentNotFound(){
        super("SchoolEnrollmentNotFound not found");
    }


    public SchoolEnrollmentNotFound(String message){
        super(message);
    }
}
