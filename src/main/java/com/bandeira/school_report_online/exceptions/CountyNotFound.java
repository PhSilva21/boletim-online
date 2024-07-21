package com.bandeira.school_report_online.exceptions;

public class CountyNotFound extends RuntimeException{

    public CountyNotFound(){
        super("County not found");
    }

    public CountyNotFound(String message){
        super(message);
    }
}
