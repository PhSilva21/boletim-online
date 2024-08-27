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
public class School {

    @Id
    private String id;


    private String name;


    private Integer vacancies;


    private County county;

    @DBRef(lazy = true)
    private List<Student> students = new ArrayList<>();


    public School(String id, String name,Integer vacancies, County county) {
        this.id = id;
        this.name = name;
        this.vacancies = vacancies;
        this.county = county;
    }
}
