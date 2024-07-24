package com.bandeira.school_report_online.exceptions;

public class SchoolWithoutVacancies extends RuntimeException{


    public SchoolWithoutVacancies(){
        super("The school currently has no vacancies");
    }

    public SchoolWithoutVacancies(String message){
        super(message);
    }
}
