package com.bandeira.school_report_online.exceptions;

public class SchoolNotFound extends RuntimeException{

    public SchoolNotFound(){
        super("School not found");
    }

    public SchoolNotFound(String message){
        super(message);
    }
}
