package com.bandeira.school_report_online.dtos;


public record StudentCreateRequest(

        String name,


        String cpf,


        String responsible,


        String celResponsible,


        String schoolName,


        String countyName
) {
}
