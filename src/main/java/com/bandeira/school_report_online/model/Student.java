package com.bandeira.school_report_online.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Student {


    @Id
    private String id;


    private String StudentRegistration;


    private String name;


    private School school;


    private Integer absences;



}
