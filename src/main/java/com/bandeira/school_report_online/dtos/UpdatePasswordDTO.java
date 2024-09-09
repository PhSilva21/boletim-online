package com.bandeira.school_report_online.dtos;

public record UpdatePasswordDTO(

        String id,

        String password,

        String newPassword,

        String passwordConfirmation) {
}
