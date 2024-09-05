package com.bandeira.school_report_online.dtos;

import java.time.LocalDate;

public record FindBySchoolReportDTO(String studentRegistration, LocalDate year) {
}
