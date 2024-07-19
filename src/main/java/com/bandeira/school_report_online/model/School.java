package com.bandeira.school_report_online.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class School {

    @Id
    private String id;


    private String name;


    private String county;
}
