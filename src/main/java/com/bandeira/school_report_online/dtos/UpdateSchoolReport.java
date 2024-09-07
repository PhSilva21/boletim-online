package com.bandeira.school_report_online.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateSchoolReport(


        String studentRegistration,


        LocalDate schoolYear,


        Integer absences,


        BigDecimal firstTwoMonthPortuguese,


        BigDecimal secondTwoMonthsPortuguese,


        BigDecimal thirdTwoMonthsPortuguese,


        BigDecimal fourthTwoMonthsPortuguese,


        BigDecimal firstTwoMonthsMathematics,


        BigDecimal secondTwoMonthsMathematics,


        BigDecimal thirdTwoMonthsMathematics,


        BigDecimal fourthTwoMonthsMathematics,


        BigDecimal firstTwoMonthsHistory,


        BigDecimal secondTwoMonthsHistory,


        BigDecimal thirdTwoMonthsHistory,


        BigDecimal fourthTwoMonthsHistory,


        BigDecimal firstTwoMonthsScience,


        BigDecimal secondTwoMonthsScience,


        BigDecimal thirdTwoMonthsScience,


        BigDecimal fourthTwoMonthsScience,


        BigDecimal firstTwoMonthsEnglish,


        BigDecimal secondTwoMonthsEnglish,


        BigDecimal thirdTwoMonthsEnglish,


        BigDecimal fourthTwoMonthsEnglish,


        BigDecimal firstTwoMonthsArt,


        BigDecimal secondTwoMonthsArt,


        BigDecimal thirdTwoMonthsArt,


        BigDecimal fourthTwoMonthsArt,


        BigDecimal firstTwoMonthsPhilosophy,


        BigDecimal secondTwoMonthsPhilosophy,


        BigDecimal thirdTwoMonthsPhilosophy,


        BigDecimal fourthTwoMonthsPhilosophy,


        BigDecimal firstTwoMonthsBiology,


        BigDecimal secondTwoMonthsBiology,


        BigDecimal thirdTwoMonthsBiology,


        BigDecimal fourthTwoMonthsBiology
) {
}
