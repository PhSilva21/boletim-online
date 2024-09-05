package com.bandeira.school_report_online.dtos;

import com.bandeira.school_report_online.model.Student;

import java.time.LocalDate;

public record CreateSchoolReportDTO(

        Double absences,


        String studentRegistration,


        LocalDate year,


        Double firstTwoMonthPortuguese,


        Double secondTwoMonthsPortuguese,


        Double thirdTwoMonthsPortuguese,


        Double fourthTwoMonthsPortuguese,


        Double firstTwoMonthsMathematics,


        Double secondTwoMonthsMathematics,


        Double thirdTwoMonthsMathematics,


        Double fourthTwoMonthsMathematics,


        Double firstTwoMonthsHistory,


        Double secondTwoMonthsHistory,


        Double thirdTwoMonthsHistory,


        Double fourthTwoMonthsHistory,


        Double firstTwoMonthsScience,


        Double secondTwoMonthsScience,


        Double thirdTwoMonthsScience,


        Double fourthTwoMonthsScience,


        Double firstTwoMonthsEnglish,


        Double secondTwoMonthsEnglish,


        Double thirdTwoMonthsEnglish,


        Double fourthTwoMonthsEnglish,


        Double firstTwoMonthsArt,


        Double secondTwoMonthsArt,


        Double thirdTwoMonthsArt,


        Double fourthTwoMonthsArt,


        Double firstTwoMonthsPhilosophy,


        Double secondTwoMonthsPhilosophy,


        Double thirdTwoMonthsPhilosophy,


        Double fourthTwoMonthsPhilosophy,


        Double firstTwoMonthsBiology,


        Double secondTwoMonthsBiology,


        Double thirdTwoMonthsBiology,


        Double fourthTwoMonthsBiology,


         Double finalMediaPortuguese,


        Double finalMediaMathematics,


        Double finalMediaHistory,


        Double finalMediaScience,


        Double finalMediaHistoryEnglish,


        Double finalMediaArt,


        Double finalMediaPhilosophy,


        Double finalMediaBiology
) {
}
