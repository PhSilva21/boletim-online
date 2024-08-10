package com.bandeira.school_report_online.exceptions;

public class EmailException extends RuntimeException {


    public EmailException(){
        super("This email is already registered to another account, log in.");
    }

    public EmailException(String message){
        super(message);
    }
}
