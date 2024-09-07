package com.bandeira.school_report_online.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class SchoolReport {


    @Id
    private String id;


    private Integer absences;


    private String nameStudent;


    private String studentRegistration;


    private LocalDate schoolYear;


    private BigDecimal firstTwoMonthPortuguese;


    private BigDecimal secondTwoMonthsPortuguese;


    private BigDecimal thirdTwoMonthsPortuguese;


    private BigDecimal fourthTwoMonthsPortuguese;


    private BigDecimal firstTwoMonthsMathematics;


    private BigDecimal secondTwoMonthsMathematics;


    private BigDecimal thirdTwoMonthsMathematics;


    private BigDecimal fourthTwoMonthsMathematics;


    private BigDecimal firstTwoMonthsHistory;


    private BigDecimal secondTwoMonthsHistory;


    private BigDecimal thirdTwoMonthsHistory;


    private BigDecimal fourthTwoMonthsHistory;


    private BigDecimal firstTwoMonthsScience;


    private BigDecimal secondTwoMonthsScience;


    private BigDecimal thirdTwoMonthsScience;


    private BigDecimal fourthTwoMonthsScience;


    private BigDecimal firstTwoMonthsEnglish;


    private BigDecimal secondTwoMonthsEnglish;


    private BigDecimal thirdTwoMonthsEnglish;


    private BigDecimal fourthTwoMonthsEnglish;


    private BigDecimal firstTwoMonthsArt;


    private BigDecimal secondTwoMonthsArt;


    private BigDecimal thirdTwoMonthsArt;


    private BigDecimal fourthTwoMonthsArt;


    private BigDecimal firstTwoMonthsPhilosophy;


    private BigDecimal secondTwoMonthsPhilosophy;


    private BigDecimal thirdTwoMonthsPhilosophy;


    private BigDecimal fourthTwoMonthsPhilosophy;


    private BigDecimal firstTwoMonthsBiology;


    private BigDecimal secondTwoMonthsBiology;


    private BigDecimal thirdTwoMonthsBiology;


    private BigDecimal fourthTwoMonthsBiology;


    private BigDecimal finalMediaPortuguese;


    private BigDecimal finalMediaMathematics;


    private BigDecimal finalMediaHistory;


    private BigDecimal finalMediaScience;


    private BigDecimal finalMediaEnglish;


    private BigDecimal finalMediaArt;


    private BigDecimal finalMediaPhilosophy;


    private BigDecimal finalMediaBiology;



}
