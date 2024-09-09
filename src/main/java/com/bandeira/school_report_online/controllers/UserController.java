package com.bandeira.school_report_online.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.bandeira.school_report_online.dtos.UpdateEmailDTO;
import com.bandeira.school_report_online.dtos.UpdatePasswordDTO;
import com.bandeira.school_report_online.dtos.UserRequest;
import com.bandeira.school_report_online.model.User;
import com.bandeira.school_report_online.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;



import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController{

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserRequest> registerUser(@RequestBody @Valid UserRequest userRequest) throws JsonProcessingException, MessagingException, UnsupportedEncodingException {
        userService.createUser(userRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<UserDetails> findByEmail(@RequestParam @Param("email") String email){
        var response = userService.findByEmail(email) ;
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("updateEmail")
    public ResponseEntity<Void> updateEmail(@RequestBody UpdateEmailDTO updateEmailDTO) {
        userService.updateEmail(updateEmailDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        userService.updatePassword(updatePasswordDTO);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/todos")
    private List<User> getAll(){
        return userService.findAll();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
