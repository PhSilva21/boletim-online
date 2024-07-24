package com.bandeira.school_report_online.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class SchoolEnrollment {

    private String id;


    private Student student;


    private School school;


    private String protocol;


    private StatusSchoolEnrollment statusSchoolEnrollment;
}
