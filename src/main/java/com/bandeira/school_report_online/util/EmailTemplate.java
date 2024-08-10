package com.bandeira.school_report_online.util;

import com.bandeira.school_report_online.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailTemplate {


    @Autowired
    private JavaMailSender emailSender;


    public void emailSchoolEnrollment(User user){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("pedro.amp002@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("SchoolEnrollment...");
        message.setText("A matricula...");


        emailSender.send(message);
    }

    public void accountCreateEmail(User user) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("pedro.amp002@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Bem-vindo ao sistema de ensino...");
        message.setText("Bem-vindo...");

        emailSender.send(message);
    }
}
