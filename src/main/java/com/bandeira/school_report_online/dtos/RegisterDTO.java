package com.bandeira.school_report_online.dtos;

import com.bandeira.school_report_online.model.UserRole;

public record RegisterDTO(String email, String password, UserRole userRole) {
}
