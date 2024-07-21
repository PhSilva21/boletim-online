package com.bandeira.school_report_online.dtos;

import lombok.Setter;


public record StudentCreateRequest(

        String name,


        String schoolName,


        String county
) {
}
