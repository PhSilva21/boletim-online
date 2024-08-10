package com.bandeira.school_report_online.util;

import com.bandeira.school_report_online.model.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class EmailTemplate {


    @Autowired
    private JavaMailSender emailSender;

    public String setFrom = "pedro.amp002@gmail.com";
    public String senderName = "Escola Hipotética";

    public void emailSchoolEnrollment(User user) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(setFrom, senderName);
        helper.setTo(user.getEmail());
        helper.setSubject("SchoolEnrollment...");
        helper.setText("A matricula...");


        emailSender.send(message);
    }

    public void accountCreateEmail(User user) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(setFrom, senderName);
        helper.setTo(user.getEmail());
        helper.setSubject("Bem-vindo...");
        helper.setText("A escola...");

        emailSender.send(message);
    }
}
