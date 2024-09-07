package com.bandeira.school_report_online.exceptions;

public class SchoolReportNotFoundException extends RuntimeException {

    public SchoolReportNotFoundException(){
        super("SchoolReport not found");
    }

    public SchoolReportNotFoundException(String message){
        super(message);
    }
}
