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


    private Student student;


    private LocalDate year;


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


    private Double finalMediaHistoryEnglish;


    private Double finalMediaArt;


    private Double finalMediaPhilosophy;


    private Double finalMediaBiology;


    public SchoolReport(Student student, Double firstTwoMonthPortuguese,Double secondTwoMonthsPortuguese, Double thirdTwoMonthsPortuguese
            , Double fourthTwoMonthsPortuguese, Double firstTwoMonthsMathematics
            , Double secondTwoMonthsMathematics, Double thirdTwoMonthsMathematics
            , Double fourthTwoMonthsMathematics, Double firstTwoMonthsHistory
            , Double secondTwoMonthsHistory, Double thirdTwoMonthsHistory, Double fourthTwoMonthsHistory
            , Double firstTwoMonthsScience, Double secondTwoMonthsScience, Double thirdTwoMonthsScience
            , Double fourthTwoMonthsScience, Double firstTwoMonthsEnglish, Double secondTwoMonthsEnglish
            , Double thirdTwoMonthsEnglish, Double fourthTwoMonthsEnglish, Double firstTwoMonthsArt
            , Double secondTwoMonthsArt, Double thirdTwoMonthsArt, Double fourthTwoMonthsArt
            , Double firstTwoMonthsPhilosophy, Double secondTwoMonthsPhilosophy
            , Double thirdTwoMonthsPhilosophy, Double fourthTwoMonthsPhilosophy, Double firstTwoMonthsBiology
            , Double secondTwoMonthsBiology, Double thirdTwoMonthsBiology, Double fourthTwoMonthsBiology
            , Double finalMediaHistory) {
        this.student = student;
        this.secondTwoMonthsPortuguese = 0.0;
        this.thirdTwoMonthsPortuguese = 0.0;
        this.fourthTwoMonthsPortuguese = 0.0;
        this.firstTwoMonthsMathematics = 0.0;
        this.secondTwoMonthsMathematics = 0.0;
        this.thirdTwoMonthsMathematics = 0.0;
        this.fourthTwoMonthsMathematics = 0.0;
        this.firstTwoMonthsHistory = 0.0;
        this.secondTwoMonthsHistory = 0.0;
        this.thirdTwoMonthsHistory = 0.0;
        this.fourthTwoMonthsHistory = 0.0;
        this.firstTwoMonthsScience = 0.0;
        this.secondTwoMonthsScience = 0.0;
        this.thirdTwoMonthsScience = 0.0;
        this.fourthTwoMonthsScience = 0.0;
        this.firstTwoMonthsEnglish = 0.0;
        this.secondTwoMonthsEnglish = 0.0;
        this.thirdTwoMonthsEnglish = 0.0;
        this.fourthTwoMonthsEnglish = 0.0;
        this.firstTwoMonthsArt = 0.0;
        this.secondTwoMonthsArt = 0.0;
        this.thirdTwoMonthsArt = 0.0;
        this.fourthTwoMonthsArt = 0.0;
        this.firstTwoMonthsPhilosophy = 0.0;
        this.secondTwoMonthsPhilosophy = 0.0;
        this.thirdTwoMonthsPhilosophy = 0.0;
        this.fourthTwoMonthsPhilosophy = 0.0;
        this.firstTwoMonthsBiology = 0.0;
        this.secondTwoMonthsBiology = 0.0;
        this.thirdTwoMonthsBiology = 0.0;
        this.fourthTwoMonthsBiology = 0.0;
        this.finalMediaHistory = CalcMediaHistory();

    }

    public Double CalcMediaHistory() {
        return firstTwoMonthsHistory * secondTwoMonthsHistory * thirdTwoMonthsHistory * fourthTwoMonthsHistory / 4;
    }

    public void setFinalMediaHistory(Double finalMediaHistory) {
        this.finalMediaHistory = finalMediaHistory;
    }
}
