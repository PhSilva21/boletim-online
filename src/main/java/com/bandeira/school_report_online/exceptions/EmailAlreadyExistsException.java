package com.bandeira.school_report_online.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {


    public EmailAlreadyExistsException(){
        super("This email is already registered to another account, log in.");
    }

    public EmailAlreadyExistsException(String message){
        super(message);
    }
}
