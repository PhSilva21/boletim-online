package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.FindBySchoolReportDTO;
import com.bandeira.school_report_online.dtos.UpdateSchoolReport;
import com.bandeira.school_report_online.exceptions.StudentNotFound;
import com.bandeira.school_report_online.model.SchoolReport;
import com.bandeira.school_report_online.repositories.SchoolReportRepository;

import com.bandeira.school_report_online.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SchoolReportService {


    @Autowired
    private SchoolReportRepository schoolReportRepository;

    @Autowired
    private StudentRepository studentRepository;


    public SchoolReport findByStudent(FindBySchoolReportDTO findBySchoolReportDTO){
        var student = studentRepository.findByStudentRegistration(findBySchoolReportDTO.StudentRegistration());

        if (student == null){
            throw new StudentNotFound();
        }

        var schoolReport = student.getSchoolReports().stream().filter(
                s -> s.getYear().equals(findBySchoolReportDTO.year())).toList();

        return schoolReport.get(1);
    }



    public SchoolReport updateSchoolReport(UpdateSchoolReport updateSchoolReport){

        var schoolReport = findByStudent(new FindBySchoolReportDTO(updateSchoolReport.StudentRegistration()
                ,updateSchoolReport.year()));


        if (updateSchoolReport.firstTwoMonthPortuguese() != 0.0){
            schoolReport.setFirstTwoMonthPortuguese(updateSchoolReport.firstTwoMonthPortuguese());
        }

        return schoolReport;
    }
}
