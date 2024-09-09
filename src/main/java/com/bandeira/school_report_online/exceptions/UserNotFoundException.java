package com.bandeira.school_report_online.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("User not found.");
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
