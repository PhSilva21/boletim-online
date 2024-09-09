package com.bandeira.school_report_online.dtos;

import com.bandeira.school_report_online.model.County;

public record SchoolCreateRequest(


        String name,

        Integer vacancies,

        String countyName

) {
}
