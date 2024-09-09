package com.bandeira.school_report_online.exceptions;

public class PasswordsDoNotMatch extends RuntimeException{

    public PasswordsDoNotMatch(){
        super("Password do no match");
    }

    public PasswordsDoNotMatch(String message){
        super(message);
    }
}
