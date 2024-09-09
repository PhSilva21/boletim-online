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
public class County {


    @Id
    private String id;


    private String name;


    private String uf;

    @DBRef(lazy = true)
    private List<School> schools = new ArrayList<>();

    @DBRef(lazy = true)
    private List<Student> students = new ArrayList<>();

    public County(String id, String name, String uf) {
        this.id = id;
        this.name = name;
        this.uf = uf;
    }
}
