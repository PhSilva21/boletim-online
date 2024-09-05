package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.CreateSchoolReportDTO;
import com.bandeira.school_report_online.dtos.FindBySchoolReportDTO;
import com.bandeira.school_report_online.dtos.UpdateSchoolReport;
import com.bandeira.school_report_online.exceptions.StudentNotFound;
import com.bandeira.school_report_online.model.SchoolReport;
import com.bandeira.school_report_online.repositories.SchoolReportRepository;

import com.bandeira.school_report_online.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class SchoolReportService {


    @Autowired
    private SchoolReportRepository schoolReportRepository;

    @Autowired
    private StudentRepository studentRepository;


    public SchoolReport createSchoolReport(CreateSchoolReportDTO createSchoolReportDTO){
        var student = studentRepository.findByStudentRegistration(createSchoolReportDTO.studentRegistration());

        if(student == null){
            throw new StudentNotFound();
        }

        SchoolReport schoolReport = new SchoolReport(
                UUID.randomUUID().toString(),
                createSchoolReportDTO.year(),
                student.getName(),
                student.getStudentRegistration());

        if(createSchoolReportDTO.firstTwoMonthPortuguese() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.secondTwoMonthsPortuguese() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.thirdTwoMonthsPortuguese() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.fourthTwoMonthsPortuguese() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.firstTwoMonthsMathematics() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.secondTwoMonthsMathematics() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.thirdTwoMonthsMathematics() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.fourthTwoMonthsMathematics() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.firstTwoMonthsHistory() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.secondTwoMonthsHistory() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.thirdTwoMonthsHistory() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.fourthTwoMonthsHistory() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.firstTwoMonthsScience() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.secondTwoMonthsScience() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.thirdTwoMonthsScience() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.fourthTwoMonthsScience() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.firstTwoMonthsEnglish() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.secondTwoMonthsEnglish() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.thirdTwoMonthsEnglish() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.fourthTwoMonthsEnglish() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.firstTwoMonthsArt() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.secondTwoMonthsArt() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.thirdTwoMonthsArt() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.fourthTwoMonthsArt() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.firstTwoMonthsPhilosophy() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.secondTwoMonthsPhilosophy() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.thirdTwoMonthsPhilosophy() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.fourthTwoMonthsPhilosophy() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.firstTwoMonthsBiology() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.secondTwoMonthsBiology() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.thirdTwoMonthsBiology() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.fourthTwoMonthsBiology() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.finalMediaPortuguese() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.finalMediaMathematics() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.finalMediaHistory() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.finalMediaScience() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.finalMediaHistoryEnglish() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.finalMediaArt() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.finalMediaPhilosophy() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }
        if(createSchoolReportDTO.finalMediaBiology() == null){
            schoolReport.setFirstTwoMonthPortuguese(0.0);
        }

        student.getSchoolReports().add(schoolReport);

        studentRepository.save(student);

        return schoolReportRepository.save(schoolReport);
    }


    public SchoolReport findByStudent(FindBySchoolReportDTO findBySchoolReportDTO){
        var student = studentRepository.findByStudentRegistration(findBySchoolReportDTO.studentRegistration());

        if (student == null){
            throw new StudentNotFound();
        }

        var schoolReport = student.getSchoolReports().stream().filter(
                s -> s.getSchoolYear().getYear() == findBySchoolReportDTO.year().getYear()).toList();

        return schoolReport.get(0);
    }



    public SchoolReport updateSchoolReport(UpdateSchoolReport updateSchoolReport){

        var student = studentRepository.findByStudentRegistration(updateSchoolReport.studentRegistration());

        if(student == null){
            throw new StudentNotFound();
        }

        var schoolReportList = student.getSchoolReports().stream().filter(s -> s.getSchoolYear().getYear() ==
                updateSchoolReport.schoolYear().getYear()).toList();


        var schoolReport = schoolReportList.get(0);

        if(schoolReport == null){
            throw new StudentNotFound();
        }

        schoolReport.setFirstTwoMonthPortuguese(updateSchoolReport.firstTwoMonthPortuguese());
        schoolReport.setSecondTwoMonthsPortuguese(updateSchoolReport.secondTwoMonthsPortuguese());
        schoolReport.setThirdTwoMonthsPortuguese(updateSchoolReport.thirdTwoMonthsPortuguese());
        schoolReport.setFourthTwoMonthsPortuguese(updateSchoolReport.fourthTwoMonthsPortuguese());

        schoolReport.setFirstTwoMonthsMathematics(updateSchoolReport.firstTwoMonthsMathematics());

        schoolReport.setSecondTwoMonthsMathematics(updateSchoolReport.secondTwoMonthsMathematics());
        schoolReport.setThirdTwoMonthsMathematics(updateSchoolReport.thirdTwoMonthsMathematics());
        schoolReport.setFourthTwoMonthsMathematics(updateSchoolReport.fourthTwoMonthsMathematics());
        schoolReport.setFirstTwoMonthsHistory(updateSchoolReport.firstTwoMonthsHistory());
        schoolReport.setSecondTwoMonthsHistory(updateSchoolReport.secondTwoMonthsHistory());
        schoolReport.setThirdTwoMonthsHistory(updateSchoolReport.thirdTwoMonthsHistory());
        schoolReport.setFourthTwoMonthsHistory(updateSchoolReport.fourthTwoMonthsHistory());
        schoolReport.setSecondTwoMonthsScience(updateSchoolReport.secondTwoMonthsScience());
        schoolReport.setThirdTwoMonthsScience(updateSchoolReport.thirdTwoMonthsScience());
        schoolReport.setFourthTwoMonthsScience(updateSchoolReport.fourthTwoMonthsScience());
        schoolReport.setFirstTwoMonthsEnglish(updateSchoolReport.firstTwoMonthsEnglish());
        schoolReport.setSecondTwoMonthsEnglish(updateSchoolReport.secondTwoMonthsEnglish());
        schoolReport.setThirdTwoMonthsEnglish(updateSchoolReport.thirdTwoMonthsEnglish());
        schoolReport.setFourthTwoMonthsEnglish(updateSchoolReport.fourthTwoMonthsEnglish());
        schoolReport.setFirstTwoMonthsArt(updateSchoolReport.firstTwoMonthsArt());
        schoolReport.setSecondTwoMonthsArt(updateSchoolReport.secondTwoMonthsArt());
        schoolReport.setThirdTwoMonthsArt(updateSchoolReport.secondTwoMonthsArt());
        schoolReport.setFourthTwoMonthsArt(updateSchoolReport.fourthTwoMonthsArt());
        schoolReport.setFirstTwoMonthsPhilosophy(updateSchoolReport.firstTwoMonthsPhilosophy());
        schoolReport.setSecondTwoMonthsPhilosophy(updateSchoolReport.secondTwoMonthsPhilosophy());
        schoolReport.setThirdTwoMonthsPhilosophy(updateSchoolReport.thirdTwoMonthsPhilosophy());
        schoolReport.setFourthTwoMonthsPhilosophy(updateSchoolReport.fourthTwoMonthsPhilosophy());
        schoolReport.setFirstTwoMonthsBiology(updateSchoolReport.firstTwoMonthsBiology());
        schoolReport.setSecondTwoMonthsBiology(updateSchoolReport.secondTwoMonthsBiology());
        schoolReport.setThirdTwoMonthsBiology(updateSchoolReport.thirdTwoMonthsBiology());
        schoolReport.setFourthTwoMonthsBiology(updateSchoolReport.fourthTwoMonthsBiology());

        if(schoolReport.getFirstTwoMonthPortuguese() > 0.0 && schoolReport.getSecondTwoMonthsPortuguese() > 0.0
                && schoolReport.getThirdTwoMonthsPortuguese() > 0.0
                && schoolReport.getFourthTwoMonthsPortuguese() > 0.0){

            var media = schoolReport.getFirstTwoMonthPortuguese() + schoolReport.getSecondTwoMonthsPortuguese()
                    + schoolReport.getThirdTwoMonthsPortuguese() + schoolReport.getFourthTwoMonthsPortuguese();

            schoolReport.setFinalMediaPortuguese(media/4);
        }

        if(schoolReport.getFirstTwoMonthsMathematics() > 0.0
                && schoolReport.getSecondTwoMonthsMathematics() > 0.0
                && schoolReport.getThirdTwoMonthsMathematics() > 0.0
                && schoolReport.getFourthTwoMonthsMathematics() > 0.0){

            var media = schoolReport.getFinalMediaMathematics() + schoolReport.getSecondTwoMonthsMathematics()
                    + schoolReport.getThirdTwoMonthsMathematics() + schoolReport.getFourthTwoMonthsMathematics();

            schoolReport.setFinalMediaMathematics(media/4);
        }

        if(schoolReport.getFirstTwoMonthsHistory() > 0.0
                && schoolReport.getSecondTwoMonthsHistory() > 0.0
                && schoolReport.getThirdTwoMonthsHistory() > 0.0
                && schoolReport.getFourthTwoMonthsHistory() > 0.0){

            var media = schoolReport.getFirstTwoMonthsHistory() + schoolReport.getSecondTwoMonthsHistory()
                    + schoolReport.getThirdTwoMonthsHistory() + schoolReport.getFourthTwoMonthsHistory();

            schoolReport.setFinalMediaHistory(media/4);
        }

        if(schoolReport.getFirstTwoMonthsScience() > 0.0
                && schoolReport.getSecondTwoMonthsScience() > 0.0
                && schoolReport.getThirdTwoMonthsScience() > 0.0
                && schoolReport.getFourthTwoMonthsScience() > 0.0){

            var media = schoolReport.getFirstTwoMonthsHistory() + schoolReport.getSecondTwoMonthsScience()
                    + schoolReport.getThirdTwoMonthsScience() + schoolReport.getFourthTwoMonthsScience();

            schoolReport.setFinalMediaScience(media/4);
        }

        if(schoolReport.getFirstTwoMonthsEnglish() > 0.0
                && schoolReport.getSecondTwoMonthsEnglish() > 0.0
                && schoolReport.getThirdTwoMonthsEnglish() > 0.0
                && schoolReport.getFourthTwoMonthsEnglish() > 0.0){

            var media = schoolReport.getFirstTwoMonthsEnglish() + schoolReport.getSecondTwoMonthsEnglish()
                    + schoolReport.getThirdTwoMonthsEnglish() + schoolReport.getFourthTwoMonthsEnglish();

            schoolReport.setFinalMediaEnglish(media/4);
        }

        if(schoolReport.getFirstTwoMonthsArt() > 0.0
                && schoolReport.getSecondTwoMonthsArt() > 0.0
                && schoolReport.getThirdTwoMonthsArt() > 0.0
                && schoolReport.getFourthTwoMonthsArt() > 0.0){

            var media = schoolReport.getFirstTwoMonthsEnglish() + schoolReport.getSecondTwoMonthsArt()
                    + schoolReport.getThirdTwoMonthsArt() + schoolReport.getFourthTwoMonthsArt();

            schoolReport.setFinalMediaArt(media/4);
        }

        if(schoolReport.getFirstTwoMonthsPhilosophy() > 0.0
                && schoolReport.getSecondTwoMonthsPhilosophy() > 0.0
                && schoolReport.getThirdTwoMonthsPhilosophy() > 0.0
                && schoolReport.getFourthTwoMonthsPhilosophy() > 0.0){

            var media = schoolReport.getFirstTwoMonthsPhilosophy() + schoolReport.getSecondTwoMonthsPhilosophy()
                    + schoolReport.getThirdTwoMonthsPhilosophy() + schoolReport.getFourthTwoMonthsPhilosophy();

            schoolReport.setFinalMediaPhilosophy(media/4);
        }

        if(schoolReport.getFirstTwoMonthsBiology() > 0.0
                && schoolReport.getSecondTwoMonthsBiology() > 0.0
                && schoolReport.getThirdTwoMonthsBiology() > 0.0
                && schoolReport.getFourthTwoMonthsBiology() > 0.0){

            var media = schoolReport.getFirstTwoMonthsBiology() + schoolReport.getSecondTwoMonthsBiology()
                    + schoolReport.getThirdTwoMonthsBiology() + schoolReport.getFourthTwoMonthsBiology();

            schoolReport.setFinalMediaBiology(media/4);
        }

        schoolReportRepository.save(schoolReport);



        return schoolReport;
    }
}
