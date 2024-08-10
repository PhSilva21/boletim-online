package com.bandeira.school_report_online.dtos;

import com.bandeira.school_report_online.model.UserRole;

public record UserRequest(String email, String password, String name, UserRole role) {
}
