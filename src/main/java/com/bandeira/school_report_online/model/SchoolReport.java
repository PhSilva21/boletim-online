package com.bandeira.school_report_online.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
@Document
public class SchoolReport {


    @Id
    private String id;


    private Double absences;


    private String nameStudent;


    private String studentRegistration;


    private LocalDate schoolYear;


    private Double firstTwoMonthPortuguese;


    private Double secondTwoMonthsPortuguese;


    private Double thirdTwoMonthsPortuguese;


    private Double fourthTwoMonthsPortuguese;


    private Double firstTwoMonthsMathematics;


    private Double secondTwoMonthsMathematics;


    private Double thirdTwoMonthsMathematics;


    private Double fourthTwoMonthsMathematics;


    private Double firstTwoMonthsHistory;


    private Double secondTwoMonthsHistory;


    private Double thirdTwoMonthsHistory;


    private Double fourthTwoMonthsHistory;


    private Double firstTwoMonthsScience;


    private Double secondTwoMonthsScience;


    private Double thirdTwoMonthsScience;


    private Double fourthTwoMonthsScience;


    private Double firstTwoMonthsEnglish;


    private Double secondTwoMonthsEnglish;


    private Double thirdTwoMonthsEnglish;


    private Double fourthTwoMonthsEnglish;


    private Double firstTwoMonthsArt;


    private Double secondTwoMonthsArt;


    private Double thirdTwoMonthsArt;


    private Double fourthTwoMonthsArt;


    private Double firstTwoMonthsPhilosophy;


    private Double secondTwoMonthsPhilosophy;


    private Double thirdTwoMonthsPhilosophy;


    private Double fourthTwoMonthsPhilosophy;


    private Double firstTwoMonthsBiology;


    private Double secondTwoMonthsBiology;


    private Double thirdTwoMonthsBiology;


    private Double fourthTwoMonthsBiology;


    private Double finalMediaPortuguese;


    private Double finalMediaMathematics;


    private Double finalMediaHistory;


    private Double finalMediaScience;


    private Double finalMediaEnglish;


    private Double finalMediaArt;


    private Double finalMediaPhilosophy;


    private Double finalMediaBiology;


    public SchoolReport(String id, LocalDate schoolYear, String nameStudent, String studentRegistration) {
        this.schoolYear = schoolYear;
        this.id = id;
        this.nameStudent = nameStudent;
        this.studentRegistration = studentRegistration;
    }
}
