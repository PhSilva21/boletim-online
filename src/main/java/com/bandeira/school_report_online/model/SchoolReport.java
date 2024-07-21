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
public class SchoolReport {

    @Id
    private String id;

    private Double absences;


    private Student student;


    private Double portuguese;


    private Double mathematics;


    private Double history;


    private Double science;


    private Double english;


    private Double art;


    private Double philosophy;


    private Double biology;



}
