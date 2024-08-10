package com.bandeira.school_report_online.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Document
public class Student {


    @Id
    private String id;


    private String studentRegistration;


    private String name;


    private String cpf;


    private String responsible;


    private Long celResponsible;


    private County county;


    private School school;


    @DBRef(lazy = true)
    private List<SchoolReport> schoolReports = new ArrayList<>();


    public Student(String id, String studentRegistration, String name, String cpf, String responsible
            , Long celResponsible, County county, School school) {
        this.id = id;
        this.studentRegistration = studentRegistration;
        this.name = name;
        this.cpf = cpf;
        this.responsible = responsible;
        this.celResponsible = celResponsible;
        this.county = county;
        this.school = school;
    }
}
