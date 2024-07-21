package com.bandeira.school_report_online.model;

import com.bandeira.school_report_online.dtos.County;
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
public class School {

    @Id
    private String id;


    private String name;


    private County county;
}
