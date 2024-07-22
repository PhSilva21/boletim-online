package com.bandeira.school_report_online.model;

import lombok.Getter;

@Getter
public enum UserRole {

    ADMIN("user"),

    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
}
