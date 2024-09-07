package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.CreateSchoolReportDTO;
import com.bandeira.school_report_online.dtos.FindBySchoolReportDTO;
import com.bandeira.school_report_online.dtos.UpdateSchoolReport;
import com.bandeira.school_report_online.exceptions.SchoolReportNotFoundException;
import com.bandeira.school_report_online.exceptions.StudentNotFound;
import com.bandeira.school_report_online.model.SchoolReport;
import com.bandeira.school_report_online.repositories.SchoolReportRepository;

import com.bandeira.school_report_online.repositories.StudentRepository;
import com.bandeira.school_report_online.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
                createSchoolReportDTO.absences(),
                student.getName(),
                RandomString.generateRandomString(12),
                createSchoolReportDTO.year(),
                createSchoolReportDTO.firstTwoMonthPortuguese(),
                createSchoolReportDTO.secondTwoMonthsPortuguese(),
                createSchoolReportDTO.thirdTwoMonthsPortuguese(),
                createSchoolReportDTO.fourthTwoMonthsPortuguese(),
                createSchoolReportDTO.firstTwoMonthsMathematics(),
                createSchoolReportDTO.secondTwoMonthsMathematics(),
                createSchoolReportDTO.thirdTwoMonthsMathematics(),
                createSchoolReportDTO.fourthTwoMonthsMathematics(),
                createSchoolReportDTO.firstTwoMonthsHistory(),
                createSchoolReportDTO.secondTwoMonthsHistory(),
                createSchoolReportDTO.thirdTwoMonthsHistory(),
                createSchoolReportDTO.fourthTwoMonthsHistory(),
                createSchoolReportDTO.firstTwoMonthsScience(),
                createSchoolReportDTO.secondTwoMonthsScience(),
                createSchoolReportDTO.thirdTwoMonthsScience(),
                createSchoolReportDTO.fourthTwoMonthsScience(),
                createSchoolReportDTO.firstTwoMonthsEnglish(),
                createSchoolReportDTO.secondTwoMonthsEnglish(),
                createSchoolReportDTO.thirdTwoMonthsEnglish(),
                createSchoolReportDTO.fourthTwoMonthsEnglish(),
                createSchoolReportDTO.firstTwoMonthsArt(),
                createSchoolReportDTO.secondTwoMonthsArt(),
                createSchoolReportDTO.thirdTwoMonthsArt(),
                createSchoolReportDTO.fourthTwoMonthsArt(),
                createSchoolReportDTO.firstTwoMonthsPhilosophy(),
                createSchoolReportDTO.secondTwoMonthsPhilosophy(),
                createSchoolReportDTO.thirdTwoMonthsPhilosophy(),
                createSchoolReportDTO.fourthTwoMonthsPhilosophy(),
                createSchoolReportDTO.firstTwoMonthsBiology(),
                createSchoolReportDTO.secondTwoMonthsBiology(),
                createSchoolReportDTO.thirdTwoMonthsBiology(),
                createSchoolReportDTO.fourthTwoMonthsBiology(),
                BigDecimal.valueOf(0.0),
                BigDecimal.valueOf(0.0),
                BigDecimal.valueOf(0.0),
                BigDecimal.valueOf(0.0),
                BigDecimal.valueOf(0.0),
                BigDecimal.valueOf(0.0),
                BigDecimal.valueOf(0.0),
                BigDecimal.valueOf(0.0)
        );

        if(createSchoolReportDTO.firstTwoMonthPortuguese() == null){
            schoolReport.setFirstTwoMonthPortuguese(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.secondTwoMonthsPortuguese() == null){
            schoolReport.setSecondTwoMonthsPortuguese(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.thirdTwoMonthsPortuguese() == null){
            schoolReport.setThirdTwoMonthsPortuguese(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.fourthTwoMonthsPortuguese() == null){
            schoolReport.setFourthTwoMonthsPortuguese(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.firstTwoMonthsMathematics() == null){
            schoolReport.setFirstTwoMonthsMathematics(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.secondTwoMonthsMathematics() == null){
            schoolReport.setSecondTwoMonthsMathematics(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.thirdTwoMonthsMathematics() == null){
            schoolReport.setThirdTwoMonthsMathematics(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.fourthTwoMonthsMathematics() == null){
            schoolReport.setFourthTwoMonthsMathematics(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.firstTwoMonthsHistory() == null){
            schoolReport.setFirstTwoMonthsHistory(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.secondTwoMonthsHistory() == null){
            schoolReport.setSecondTwoMonthsHistory(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.thirdTwoMonthsHistory() == null){
            schoolReport.setThirdTwoMonthsHistory(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.fourthTwoMonthsHistory() == null){
            schoolReport.setFourthTwoMonthsHistory(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.firstTwoMonthsScience() == null){
            schoolReport.setFirstTwoMonthsScience(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.secondTwoMonthsScience() == null){
            schoolReport.setSecondTwoMonthsScience(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.thirdTwoMonthsScience() == null){
            schoolReport.setThirdTwoMonthsScience(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.fourthTwoMonthsScience() == null){
            schoolReport.setFourthTwoMonthsScience(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.firstTwoMonthsEnglish() == null){
            schoolReport.setFirstTwoMonthsEnglish(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.secondTwoMonthsEnglish() == null){
            schoolReport.setSecondTwoMonthsEnglish(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.thirdTwoMonthsEnglish() == null){
            schoolReport.setThirdTwoMonthsEnglish(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.fourthTwoMonthsEnglish() == null){
            schoolReport.setFourthTwoMonthsEnglish(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.firstTwoMonthsArt() == null){
            schoolReport.setFirstTwoMonthsArt(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.secondTwoMonthsArt() == null){
            schoolReport.setSecondTwoMonthsArt(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.thirdTwoMonthsArt() == null){
            schoolReport.setThirdTwoMonthsArt(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.fourthTwoMonthsArt() == null){
            schoolReport.setFourthTwoMonthsArt(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.firstTwoMonthsPhilosophy() == null){
            schoolReport.setFirstTwoMonthsPhilosophy(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.secondTwoMonthsPhilosophy() == null){
            schoolReport.setSecondTwoMonthsPhilosophy(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.thirdTwoMonthsPhilosophy() == null){
            schoolReport.setThirdTwoMonthsPhilosophy(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.fourthTwoMonthsPhilosophy() == null){
            schoolReport.setFourthTwoMonthsPhilosophy(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.firstTwoMonthsBiology() == null){
            schoolReport.setFirstTwoMonthsBiology(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.secondTwoMonthsBiology() == null){
            schoolReport.setSecondTwoMonthsBiology(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.thirdTwoMonthsBiology() == null){
            schoolReport.setThirdTwoMonthsBiology(BigDecimal.valueOf(0.0));
        }
        if(createSchoolReportDTO.fourthTwoMonthsBiology() == null){
            schoolReport.setFourthTwoMonthsBiology(BigDecimal.valueOf(0.0));
        }
        schoolReport.setFinalMediaPortuguese(BigDecimal.valueOf(0.0));

        schoolReport.setFinalMediaMathematics(BigDecimal.valueOf(0.0));

        schoolReport.setFinalMediaHistory(BigDecimal.valueOf(0.0));

        schoolReport.setFinalMediaEnglish(BigDecimal.valueOf(0.0));

        schoolReport.setFinalMediaEnglish(BigDecimal.valueOf(0.0));

        schoolReport.setFinalMediaArt(BigDecimal.valueOf(0.0));

        schoolReport.setFinalMediaPhilosophy(BigDecimal.valueOf(0.0));

        schoolReport.setFinalMediaBiology(BigDecimal.valueOf(0.0));


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

        if(schoolReport.isEmpty()){
            throw new SchoolReportNotFoundException();
        }

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

        schoolReport.setAbsences(updateSchoolReport.absences());
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
        schoolReport.setFirstTwoMonthsScience(updateSchoolReport.firstTwoMonthsScience());
        schoolReport.setSecondTwoMonthsScience(updateSchoolReport.secondTwoMonthsScience());
        schoolReport.setThirdTwoMonthsScience(updateSchoolReport.thirdTwoMonthsScience());
        schoolReport.setFourthTwoMonthsScience(updateSchoolReport.fourthTwoMonthsScience());
        schoolReport.setFirstTwoMonthsEnglish(updateSchoolReport.firstTwoMonthsEnglish());
        schoolReport.setSecondTwoMonthsEnglish(updateSchoolReport.secondTwoMonthsEnglish());
        schoolReport.setThirdTwoMonthsEnglish(updateSchoolReport.thirdTwoMonthsEnglish());
        schoolReport.setFourthTwoMonthsEnglish(updateSchoolReport.fourthTwoMonthsEnglish());
        schoolReport.setFirstTwoMonthsArt(updateSchoolReport.firstTwoMonthsArt());
        schoolReport.setSecondTwoMonthsArt(updateSchoolReport.secondTwoMonthsArt());
        schoolReport.setThirdTwoMonthsArt(updateSchoolReport.thirdTwoMonthsArt());
        schoolReport.setFourthTwoMonthsArt(updateSchoolReport.fourthTwoMonthsArt());
        schoolReport.setFirstTwoMonthsPhilosophy(updateSchoolReport.firstTwoMonthsPhilosophy());
        schoolReport.setSecondTwoMonthsPhilosophy(updateSchoolReport.secondTwoMonthsPhilosophy());
        schoolReport.setThirdTwoMonthsPhilosophy(updateSchoolReport.thirdTwoMonthsPhilosophy());
        schoolReport.setFourthTwoMonthsPhilosophy(updateSchoolReport.fourthTwoMonthsPhilosophy());
        schoolReport.setFirstTwoMonthsBiology(updateSchoolReport.firstTwoMonthsBiology());
        schoolReport.setSecondTwoMonthsBiology(updateSchoolReport.secondTwoMonthsBiology());
        schoolReport.setThirdTwoMonthsBiology(updateSchoolReport.thirdTwoMonthsBiology());
        schoolReport.setFourthTwoMonthsBiology(updateSchoolReport.fourthTwoMonthsBiology());

        if(schoolReport.getFirstTwoMonthPortuguese().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getSecondTwoMonthsPortuguese().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getThirdTwoMonthsPortuguese().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getFourthTwoMonthsPortuguese().compareTo(BigDecimal.valueOf(0.0)) > 0){

            BigDecimal media = schoolReport.getFirstTwoMonthPortuguese().add(schoolReport.getSecondTwoMonthsPortuguese().
                    add(schoolReport.getThirdTwoMonthsPortuguese().add(schoolReport.getFourthTwoMonthsPortuguese())));

            schoolReport.setFinalMediaPortuguese(media.divide(BigDecimal.valueOf(4)).setScale(1, RoundingMode.UP));
        }

        if(schoolReport.getFirstTwoMonthsMathematics().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getSecondTwoMonthsMathematics().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getThirdTwoMonthsMathematics().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getFourthTwoMonthsMathematics().compareTo(BigDecimal.valueOf(0.0)) > 0){

            BigDecimal media = schoolReport.getFirstTwoMonthsMathematics().add(schoolReport.getSecondTwoMonthsMathematics().
                    add(schoolReport.getThirdTwoMonthsMathematics().add(schoolReport.getFourthTwoMonthsMathematics())));

            schoolReport.setFinalMediaMathematics(media.divide(BigDecimal.valueOf(4)).setScale(1, RoundingMode.UP));
        }

        if(schoolReport.getFirstTwoMonthsHistory().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getSecondTwoMonthsHistory().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getThirdTwoMonthsHistory().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getFourthTwoMonthsHistory().compareTo(BigDecimal.valueOf(0.0)) > 0){

            BigDecimal media = schoolReport.getFirstTwoMonthsHistory().add(schoolReport.getSecondTwoMonthsHistory().
                    add(schoolReport.getThirdTwoMonthsHistory().add(schoolReport.getFourthTwoMonthsHistory())));

            schoolReport.setFinalMediaHistory(media.divide(BigDecimal.valueOf(4)).setScale(1, RoundingMode.UP));
        }

        if(schoolReport.getFirstTwoMonthsScience().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getSecondTwoMonthsScience().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getThirdTwoMonthsScience().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getFourthTwoMonthsScience().compareTo(BigDecimal.valueOf(0.0)) > 0){

            BigDecimal media = schoolReport.getFirstTwoMonthsScience().add(schoolReport.getSecondTwoMonthsScience().
                    add(schoolReport.getThirdTwoMonthsScience().add(schoolReport.getFourthTwoMonthsScience())));

            schoolReport.setFinalMediaScience(media.divide(BigDecimal.valueOf(4)).setScale(1, RoundingMode.UP));
        }


        if(schoolReport.getFirstTwoMonthsEnglish().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getSecondTwoMonthsEnglish().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getThirdTwoMonthsEnglish().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getFourthTwoMonthsEnglish().compareTo(BigDecimal.valueOf(0.0)) > 0){

            BigDecimal media = schoolReport.getFirstTwoMonthsEnglish().add(schoolReport.getSecondTwoMonthsEnglish().
                    add(schoolReport.getThirdTwoMonthsEnglish().add(schoolReport.getFourthTwoMonthsEnglish())));

            schoolReport.setFinalMediaEnglish(media.divide(BigDecimal.valueOf(4)).setScale(1, RoundingMode.UP));
        }

        if(schoolReport.getFirstTwoMonthsArt().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getSecondTwoMonthsArt().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getThirdTwoMonthsArt().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getFourthTwoMonthsArt().compareTo(BigDecimal.valueOf(0.0)) > 0){

            BigDecimal media = schoolReport.getFirstTwoMonthsArt().add(schoolReport.getSecondTwoMonthsArt().
                    add(schoolReport.getThirdTwoMonthsArt().add(schoolReport.getFourthTwoMonthsArt())));

            schoolReport.setFinalMediaArt(media.divide(BigDecimal.valueOf(4)).setScale(1, RoundingMode.UP));
        }

        if(schoolReport.getFirstTwoMonthsPhilosophy().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getSecondTwoMonthsPhilosophy().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getThirdTwoMonthsPhilosophy().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getFourthTwoMonthsPhilosophy().compareTo(BigDecimal.valueOf(0.0)) > 0){

            BigDecimal media = schoolReport.getFirstTwoMonthsPhilosophy().add(schoolReport.getSecondTwoMonthsPhilosophy().
                    add(schoolReport.getThirdTwoMonthsPhilosophy().add(schoolReport.getFourthTwoMonthsPhilosophy())));

            schoolReport.setFinalMediaPhilosophy(media.divide(BigDecimal.valueOf(4)).setScale(1, RoundingMode.UP));
        }

        if(schoolReport.getFirstTwoMonthsBiology().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getSecondTwoMonthsBiology().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getThirdTwoMonthsBiology().compareTo(BigDecimal.valueOf(0.0)) > 0
                && schoolReport.getFourthTwoMonthsBiology().compareTo(BigDecimal.valueOf(0.0)) > 0){

            BigDecimal media = schoolReport.getFirstTwoMonthsBiology().add(schoolReport.getSecondTwoMonthsBiology().
                    add(schoolReport.getThirdTwoMonthsBiology().add(schoolReport.getFourthTwoMonthsBiology())));

            schoolReport.setFinalMediaBiology(media.divide(BigDecimal.valueOf(4)).setScale(1, RoundingMode.UP));
        }


        schoolReportRepository.save(schoolReport);



        return schoolReport;

    }
}
