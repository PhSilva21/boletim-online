package com.bandeira.school_report_online.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(){
        super("Email already exists.");
    }

    public EmailAlreadyExistsException(String message){
        super(message);
    }
}
