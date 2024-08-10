package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.UserRequest;
import com.bandeira.school_report_online.exceptions.EmailException;
import com.bandeira.school_report_online.model.User;
import com.bandeira.school_report_online.repositories.UserRepository;
import com.bandeira.school_report_online.util.EmailTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.UUID;


@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;


   @Autowired
   private EmailTemplate emailTemplate;


    public void createUser(UserRequest userRequest) throws MessagingException, UnsupportedEncodingException {
        if (userRepository.findByEmail(userRequest.email()) != null) {
            throw new EmailException();
        }
            User user = new User(
                    UUID.randomUUID().toString(),
                    userRequest.email(),
                    userRequest.password(),
                    userRequest.role()
            );


        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        emailTemplate.accountCreateEmail(user);

        userRepository.save(user);

    }
}
