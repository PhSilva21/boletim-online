package com.bandeira.school_report_online.dtos;

import com.bandeira.school_report_online.model.County;
import com.bandeira.school_report_online.model.School;

public record StudentCreateResponse(String name, String studentRegistration, County county, School school) {
}
