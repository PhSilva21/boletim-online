package com.bandeira.school_report_online.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Student {


    @Id
    private String id;


    private String studentRegistration;


    private String name;


    private String county;


    private School school;



}
