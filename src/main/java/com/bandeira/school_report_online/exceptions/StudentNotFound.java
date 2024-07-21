package com.bandeira.school_report_online.exceptions;

public class StudentNotFound extends RuntimeException{

    public StudentNotFound(){
        super("Student not found");
    }

    public StudentNotFound(String message){
        super(message);
    }
}
