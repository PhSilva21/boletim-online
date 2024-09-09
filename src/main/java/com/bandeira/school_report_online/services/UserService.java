package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.UpdateEmailDTO;
import com.bandeira.school_report_online.dtos.UpdatePasswordDTO;
import com.bandeira.school_report_online.dtos.UserRequest;
import com.bandeira.school_report_online.exceptions.*;
import com.bandeira.school_report_online.model.User;
import com.bandeira.school_report_online.repositories.UserRepository;
import com.bandeira.school_report_online.util.EmailTemplate;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
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
            throw new EmailAlreadyExistsException();
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


    public UserDetails findByEmail(String email) {
        var user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException();
        } else
            return user;
    }


    public void updateEmail(UpdateEmailDTO updateEmailDTO) {

        var user = userRepository.findById(updateEmailDTO.id()).orElseThrow(UserNotFoundException::new);

        if(!updateEmailDTO.password().equals(user.getPassword())){
            throw new IncorrectPasswordException();
        }

        user.setEmail(updateEmailDTO.email());

        userRepository.save(user);
    }


    public void updatePassword(UpdatePasswordDTO updatePasswordDTO) {

        var user = userRepository.findById(updatePasswordDTO.id()).orElseThrow(UserNotFoundException::new);

        if (!user.getPassword().equals(updatePasswordDTO.password())){
            throw new IncorrectPasswordException();
        }

        if (!updatePasswordDTO.newPassword().equals(updatePasswordDTO.passwordConfirmation())){
            throw new PasswordsDoNotMatch();
        }

        user.setPassword(updatePasswordDTO.newPassword());

        userRepository.save(user);

    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(String id){
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(id);
    }

}
