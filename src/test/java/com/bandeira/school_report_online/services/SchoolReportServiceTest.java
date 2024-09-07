package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.CreateSchoolReportDTO;
import com.bandeira.school_report_online.dtos.FindBySchoolReportDTO;
import com.bandeira.school_report_online.dtos.UpdateSchoolReport;
import com.bandeira.school_report_online.exceptions.SchoolReportNotFoundException;
import com.bandeira.school_report_online.exceptions.StudentNotFound;
import com.bandeira.school_report_online.model.County;
import com.bandeira.school_report_online.model.School;
import com.bandeira.school_report_online.model.SchoolReport;
import com.bandeira.school_report_online.model.Student;
import com.bandeira.school_report_online.repositories.SchoolReportRepository;
import com.bandeira.school_report_online.repositories.StudentRepository;
import com.bandeira.school_report_online.util.RandomString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
class SchoolReportServiceTest {

    @InjectMocks
    private SchoolReportService schoolReportService;

    @Mock
    private SchoolReportRepository schoolReportRepository;

    @Mock
    private StudentRepository studentRepository;

    @Captor
    private ArgumentCaptor<SchoolReport> schoolReportArgumentCaptor;


    @Nested
    class createSchoolReport{

        CreateSchoolReportDTO createSchoolReportDTO = new CreateSchoolReportDTO(67
                , RandomString.generateRandomString(6), LocalDate.of(2024,05,17)
                , BigDecimal.valueOf(5.9), BigDecimal.valueOf(6.9),BigDecimal.valueOf( 8.3), BigDecimal.valueOf(9.2), BigDecimal.valueOf(7.1)
                ,BigDecimal.valueOf(6.9), BigDecimal.valueOf(4.8), BigDecimal.valueOf(9.4), BigDecimal.valueOf(6.2), BigDecimal.valueOf(7.4), BigDecimal.valueOf(7.7), BigDecimal.valueOf(5.8), BigDecimal.valueOf(6.2)
                , BigDecimal.valueOf(5.0), BigDecimal.valueOf(7.3), BigDecimal.valueOf(7.8), BigDecimal.valueOf(9.0), BigDecimal.valueOf(5.4), BigDecimal.valueOf(8.6), BigDecimal.valueOf(7.6)
                , BigDecimal.valueOf(8.2), BigDecimal.valueOf(6.9),BigDecimal.valueOf(7.7), BigDecimal.valueOf(6.5), BigDecimal.valueOf(7.1)
                , BigDecimal.valueOf(6.8), BigDecimal.valueOf(8.3), BigDecimal.valueOf(9.6), BigDecimal.valueOf(8.8)
                ,BigDecimal.valueOf(7.5), BigDecimal.valueOf(7.4), BigDecimal.valueOf(6.4));

        County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        Student student = new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school);

        SchoolReport schoolReport = new SchoolReport();


        FindBySchoolReportDTO findBySchoolReportDTO = new FindBySchoolReportDTO(student.getStudentRegistration(),
                LocalDate.of(2024,9,05));


        @Test
        @DisplayName("You must create the school report with successes")
        void YouMustCreateTheSchoolReportWithSuccesses(){
            doReturn(student)
                    .when(studentRepository)
                    .findByStudentRegistration(createSchoolReportDTO.studentRegistration());
            doReturn(student)
                    .when(studentRepository).save(student);
            doReturn(schoolReport)
                    .when(schoolReportRepository)
                    .save(schoolReportArgumentCaptor.capture());


            var response = schoolReportService.createSchoolReport(createSchoolReportDTO);

            var schoolReportCaptured = schoolReportArgumentCaptor.getValue();

            assertEquals(createSchoolReportDTO.absences(), schoolReportCaptured.getAbsences());
            assertEquals(createSchoolReportDTO.year(), schoolReportCaptured.getSchoolYear());
            assertEquals(createSchoolReportDTO.firstTwoMonthPortuguese(), schoolReportCaptured.getFirstTwoMonthPortuguese());
            assertEquals(createSchoolReportDTO.secondTwoMonthsPortuguese(), schoolReportCaptured.getSecondTwoMonthsPortuguese());
            assertEquals(createSchoolReportDTO.thirdTwoMonthsPortuguese(), schoolReportCaptured.getThirdTwoMonthsPortuguese());
            assertEquals(createSchoolReportDTO.fourthTwoMonthsPortuguese(), schoolReportCaptured.getFourthTwoMonthsPortuguese());
            assertEquals(createSchoolReportDTO.firstTwoMonthsMathematics(), schoolReportCaptured.getFirstTwoMonthsMathematics());
            assertEquals(createSchoolReportDTO.secondTwoMonthsMathematics(), schoolReportCaptured.getSecondTwoMonthsMathematics());
            assertEquals(createSchoolReportDTO.thirdTwoMonthsMathematics(), schoolReportCaptured.getThirdTwoMonthsMathematics());
            assertEquals(createSchoolReportDTO.fourthTwoMonthsMathematics(), schoolReportCaptured.getFourthTwoMonthsMathematics());
            assertEquals(createSchoolReportDTO.firstTwoMonthsHistory(), schoolReportCaptured.getFirstTwoMonthsHistory());
            assertEquals(createSchoolReportDTO.secondTwoMonthsHistory(), schoolReportCaptured.getSecondTwoMonthsHistory());
            assertEquals(createSchoolReportDTO.thirdTwoMonthsHistory(), schoolReportCaptured.getThirdTwoMonthsHistory());
            assertEquals(createSchoolReportDTO.fourthTwoMonthsHistory(), schoolReportCaptured.getFourthTwoMonthsHistory());
            assertEquals(createSchoolReportDTO.firstTwoMonthsScience(), schoolReportCaptured.getFirstTwoMonthsScience());
            assertEquals(createSchoolReportDTO.secondTwoMonthsScience(), schoolReportCaptured.getSecondTwoMonthsScience());
            assertEquals(createSchoolReportDTO.thirdTwoMonthsScience(), schoolReportCaptured.getThirdTwoMonthsScience());
            assertEquals(createSchoolReportDTO.fourthTwoMonthsScience(), schoolReportCaptured.getFourthTwoMonthsScience());
            assertEquals(createSchoolReportDTO.firstTwoMonthsEnglish(), schoolReportCaptured.getFirstTwoMonthsEnglish());
            assertEquals(createSchoolReportDTO.secondTwoMonthsEnglish(), schoolReportCaptured.getSecondTwoMonthsEnglish());
            assertEquals(createSchoolReportDTO.thirdTwoMonthsEnglish(), schoolReportCaptured.getThirdTwoMonthsEnglish());
            assertEquals(createSchoolReportDTO.fourthTwoMonthsEnglish(), schoolReportCaptured.getFourthTwoMonthsEnglish());
            assertEquals(createSchoolReportDTO.firstTwoMonthsArt(), schoolReportCaptured.getFirstTwoMonthsArt());
            assertEquals(createSchoolReportDTO.secondTwoMonthsArt(), schoolReportCaptured.getSecondTwoMonthsArt());
            assertEquals(createSchoolReportDTO.thirdTwoMonthsArt(), schoolReportCaptured.getThirdTwoMonthsArt());
            assertEquals(createSchoolReportDTO.fourthTwoMonthsArt(), schoolReportCaptured.getFourthTwoMonthsArt());
            assertEquals(createSchoolReportDTO.firstTwoMonthsPhilosophy(), schoolReportCaptured.getFirstTwoMonthsPhilosophy());
            assertEquals(createSchoolReportDTO.secondTwoMonthsPhilosophy(), schoolReportCaptured.getSecondTwoMonthsPhilosophy());
            assertEquals(createSchoolReportDTO.thirdTwoMonthsPhilosophy(), schoolReportCaptured.getThirdTwoMonthsPhilosophy());
            assertEquals(createSchoolReportDTO.fourthTwoMonthsPhilosophy(), schoolReportCaptured.getFourthTwoMonthsPhilosophy());
            assertEquals(createSchoolReportDTO.firstTwoMonthsBiology(), schoolReportCaptured.getFirstTwoMonthsBiology());
            assertEquals(createSchoolReportDTO.secondTwoMonthsBiology(), schoolReportCaptured.getSecondTwoMonthsBiology());
            assertEquals(createSchoolReportDTO.thirdTwoMonthsBiology(), schoolReportCaptured.getThirdTwoMonthsBiology());
            assertEquals(createSchoolReportDTO.fourthTwoMonthsBiology(), schoolReportCaptured.getFourthTwoMonthsBiology());
            assertEquals(BigDecimal.valueOf(0.0), schoolReportCaptured.getFinalMediaPortuguese());
            assertEquals(BigDecimal.valueOf(0.0), schoolReportCaptured.getFinalMediaMathematics());
            assertEquals(BigDecimal.valueOf(0.0), schoolReportCaptured.getFinalMediaHistory());
            assertEquals(BigDecimal.valueOf(0.0), schoolReportCaptured.getFinalMediaScience());
            assertEquals(BigDecimal.valueOf(0.0), schoolReportCaptured.getFinalMediaEnglish());
            assertEquals(BigDecimal.valueOf(0.0), schoolReportCaptured.getFinalMediaArt());
            assertEquals(BigDecimal.valueOf(0.0), schoolReportCaptured.getFinalMediaPhilosophy());
            assertEquals(BigDecimal.valueOf(0.0), schoolReportCaptured.getFinalMediaBiology());

        }

        @Test
        @DisplayName("You should make an exception when you can't find the student")
        void YouShouldMakeAnExceptionWhenYouCantFindTheStudent(){
            doReturn(null)
                    .when(studentRepository)
                    .findByStudentRegistration(createSchoolReportDTO.studentRegistration());

            assertThrows(StudentNotFound.class,
                    () -> schoolReportService.createSchoolReport(createSchoolReportDTO));
        }
    }


    @Nested
    class findByStudent {

        County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        Student student = new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school);

        SchoolReport schoolReport = new SchoolReport(UUID.randomUUID().toString(), 76, "Jonas Ferreira"
                , RandomString.generateRandomString(12), LocalDate.of(2024,2,11)
                , BigDecimal.valueOf(5.01), BigDecimal.valueOf(5.02), BigDecimal.valueOf(5.03), BigDecimal.valueOf(5.04), BigDecimal.valueOf(5.05),
                BigDecimal.valueOf(5.06), BigDecimal.valueOf(5.07), BigDecimal.valueOf(5.08), BigDecimal.valueOf(5.09), BigDecimal.valueOf(5.10),
                BigDecimal.valueOf(5.11), BigDecimal.valueOf(5.12), BigDecimal.valueOf(5.13), BigDecimal.valueOf(5.14), BigDecimal.valueOf(5.15),
                BigDecimal.valueOf(5.16), BigDecimal.valueOf(5.17), BigDecimal.valueOf(5.18), BigDecimal.valueOf(5.19), BigDecimal.valueOf(5.20),
                BigDecimal.valueOf(5.21), BigDecimal.valueOf(5.22), BigDecimal.valueOf(5.23), BigDecimal.valueOf(5.24), BigDecimal.valueOf(5.25),
                BigDecimal.valueOf(5.26), BigDecimal.valueOf(5.27), BigDecimal.valueOf(5.28), BigDecimal.valueOf(5.29), BigDecimal.valueOf(5.30),
                BigDecimal.valueOf(5.31), BigDecimal.valueOf(5.32), BigDecimal.valueOf(5.33), BigDecimal.valueOf(5.34), BigDecimal.valueOf(5.35),
                BigDecimal.valueOf(5.36), BigDecimal.valueOf(5.37), BigDecimal.valueOf(5.38), BigDecimal.valueOf(5.39), BigDecimal.valueOf(5.40));

        FindBySchoolReportDTO findBySchoolReportDTO = new FindBySchoolReportDTO(student.getStudentRegistration(),
                LocalDate.of(2024,9,05));

    @Test
    @DisplayName("It should return the school report successfully")
    void ItShouldReturnTheSchoolReportSuccessfully() {
        student.getSchoolReports().add(schoolReport);
        doReturn(student)
                .when(studentRepository)
                .findByStudentRegistration(findBySchoolReportDTO.studentRegistration());

        student.getSchoolReports().add(schoolReport);

        var schoolReportMock = student.getSchoolReports().stream().filter(
                s -> s.getSchoolYear().getYear() == findBySchoolReportDTO.year().getYear()).toList();

        var response = schoolReportService.findByStudent(findBySchoolReportDTO);

        assertNotNull(response);
        assertFalse(schoolReportMock.isEmpty());
        }

        @Test
        @DisplayName("You should make an exception when you can't find the student")
        void YouShouldMakeAnExceptionWhenYouCantFindTheStudent(){
        doReturn(null)
                .when(studentRepository)
                .findByStudentRegistration(findBySchoolReportDTO.studentRegistration());

        assertThrows(StudentNotFound.class,
                () -> schoolReportService.findByStudent(findBySchoolReportDTO));
        }

        @Test
        @DisplayName("You should throw an exception when you can't find the school report")
        void YouShouldThrowAnExceptionWhenYouCantFindSchoolReport(){
        doReturn(student)
                .when(studentRepository)
                .findByStudentRegistration(findBySchoolReportDTO.studentRegistration());


        assertThrows(SchoolReportNotFoundException.class,
                () -> schoolReportService.findByStudent(findBySchoolReportDTO));
        }
    }

    @Nested
    class updateSchoolReport {

        County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        Student student = new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school);

        SchoolReport schoolReport = new SchoolReport(UUID.randomUUID().toString(), 76, "Jonas Ferreira"
                , RandomString.generateRandomString(12), LocalDate.of(2024,2,11)
                , BigDecimal.valueOf(5.01), BigDecimal.valueOf(5.02), BigDecimal.valueOf(5.03), BigDecimal.valueOf(5.04), BigDecimal.valueOf(5.05),
                BigDecimal.valueOf(5.06), BigDecimal.valueOf(5.07), BigDecimal.valueOf(5.08), BigDecimal.valueOf(5.09), BigDecimal.valueOf(5.10),
                BigDecimal.valueOf(5.11), BigDecimal.valueOf(5.12), BigDecimal.valueOf(5.13), BigDecimal.valueOf(5.14), BigDecimal.valueOf(5.15),
                BigDecimal.valueOf(5.16), BigDecimal.valueOf(5.17), BigDecimal.valueOf(5.18), BigDecimal.valueOf(5.19), BigDecimal.valueOf(5.20),
                BigDecimal.valueOf(5.21), BigDecimal.valueOf(5.22), BigDecimal.valueOf(5.23), BigDecimal.valueOf(5.24), BigDecimal.valueOf(5.25),
                BigDecimal.valueOf(5.26), BigDecimal.valueOf(5.27), BigDecimal.valueOf(5.28), BigDecimal.valueOf(5.29), BigDecimal.valueOf(5.30),
                BigDecimal.valueOf(5.31), BigDecimal.valueOf(5.32), BigDecimal.valueOf(5.33), BigDecimal.valueOf(5.34), BigDecimal.valueOf(5.35),
                BigDecimal.valueOf(5.36), BigDecimal.valueOf(5.37), BigDecimal.valueOf(5.38), BigDecimal.valueOf(5.39), BigDecimal.valueOf(5.40));

        UpdateSchoolReport updateSchoolReport = new UpdateSchoolReport(student.getStudentRegistration()
                ,LocalDate.of(2024,3,16) , 72, BigDecimal.valueOf(5.01), BigDecimal.valueOf(5.02), BigDecimal.valueOf(5.03), BigDecimal.valueOf(5.04), BigDecimal.valueOf(5.05),
                BigDecimal.valueOf(5.06), BigDecimal.valueOf(5.07), BigDecimal.valueOf(5.08), BigDecimal.valueOf(5.09), BigDecimal.valueOf(5.10),
                BigDecimal.valueOf(5.11), BigDecimal.valueOf(5.12), BigDecimal.valueOf(5.13), BigDecimal.valueOf(5.14), BigDecimal.valueOf(5.15),
                BigDecimal.valueOf(5.16), BigDecimal.valueOf(5.17), BigDecimal.valueOf(5.18), BigDecimal.valueOf(5.19), BigDecimal.valueOf(5.20),
                BigDecimal.valueOf(5.21), BigDecimal.valueOf(5.22), BigDecimal.valueOf(5.23), BigDecimal.valueOf(5.24), BigDecimal.valueOf(5.25),
                BigDecimal.valueOf(5.26), BigDecimal.valueOf(5.27), BigDecimal.valueOf(5.28), BigDecimal.valueOf(5.29), BigDecimal.valueOf(5.30),
                BigDecimal.valueOf(5.31), BigDecimal.valueOf(5.32)
                );

        public BigDecimal finalMedia(BigDecimal first, BigDecimal second, BigDecimal third, BigDecimal fourth){
            return first.add(second.add(third.add(fourth)).setScale(1, RoundingMode.UP));

        }

        @Test
        @DisplayName("Must update school report successfully")
        void MustUpdateSchoolReportSuccessfully() {
            doReturn(student)
                    .when(studentRepository)
                    .findByStudentRegistration(updateSchoolReport.studentRegistration());
            doReturn(schoolReport)
                    .when(schoolReportRepository)
                    .save(schoolReportArgumentCaptor.capture());

            student.getSchoolReports().add(schoolReport);

            var response = schoolReportService.updateSchoolReport(updateSchoolReport);


            var schoolReportCaptured = schoolReportArgumentCaptor.getValue();

            assertEquals(updateSchoolReport.absences(), schoolReportCaptured.getAbsences());
            assertEquals(updateSchoolReport.firstTwoMonthPortuguese(), schoolReportCaptured.getFirstTwoMonthPortuguese());
            assertEquals(updateSchoolReport.secondTwoMonthsPortuguese(), schoolReportCaptured.getSecondTwoMonthsPortuguese());
            assertEquals(updateSchoolReport.thirdTwoMonthsPortuguese(), schoolReportCaptured.getThirdTwoMonthsPortuguese());
            assertEquals(updateSchoolReport.fourthTwoMonthsPortuguese(), schoolReportCaptured.getFourthTwoMonthsPortuguese());
            assertEquals(updateSchoolReport.firstTwoMonthsMathematics(), schoolReportCaptured.getFirstTwoMonthsMathematics());
            assertEquals(updateSchoolReport.secondTwoMonthsMathematics(), schoolReportCaptured.getSecondTwoMonthsMathematics());
            assertEquals(updateSchoolReport.thirdTwoMonthsMathematics(), schoolReportCaptured.getThirdTwoMonthsMathematics());
            assertEquals(updateSchoolReport.fourthTwoMonthsMathematics(), schoolReportCaptured.getFourthTwoMonthsMathematics());
            assertEquals(updateSchoolReport.firstTwoMonthsHistory(), schoolReportCaptured.getFirstTwoMonthsHistory());
            assertEquals(updateSchoolReport.secondTwoMonthsHistory(), schoolReportCaptured.getSecondTwoMonthsHistory());
            assertEquals(updateSchoolReport.thirdTwoMonthsHistory(), schoolReportCaptured.getThirdTwoMonthsHistory());
            assertEquals(updateSchoolReport.fourthTwoMonthsHistory(), schoolReportCaptured.getFourthTwoMonthsHistory());
            assertEquals(updateSchoolReport.firstTwoMonthsScience(), schoolReportCaptured.getFirstTwoMonthsScience());
            assertEquals(updateSchoolReport.secondTwoMonthsScience(), schoolReportCaptured.getSecondTwoMonthsScience());
            assertEquals(updateSchoolReport.thirdTwoMonthsScience(), schoolReportCaptured.getThirdTwoMonthsScience());
            assertEquals(updateSchoolReport.fourthTwoMonthsScience(), schoolReportCaptured.getFourthTwoMonthsScience());
            assertEquals(updateSchoolReport.firstTwoMonthsEnglish(), schoolReportCaptured.getFirstTwoMonthsEnglish());
            assertEquals(updateSchoolReport.secondTwoMonthsEnglish(), schoolReportCaptured.getSecondTwoMonthsEnglish());
            assertEquals(updateSchoolReport.thirdTwoMonthsEnglish(), schoolReportCaptured.getThirdTwoMonthsEnglish());
            assertEquals(updateSchoolReport.fourthTwoMonthsEnglish(), schoolReportCaptured.getFourthTwoMonthsEnglish());
            assertEquals(updateSchoolReport.firstTwoMonthsArt(), schoolReportCaptured.getFirstTwoMonthsArt());
            assertEquals(updateSchoolReport.secondTwoMonthsArt(), schoolReportCaptured.getSecondTwoMonthsArt());
            assertEquals(updateSchoolReport.thirdTwoMonthsArt(), schoolReportCaptured.getThirdTwoMonthsArt());
            assertEquals(updateSchoolReport.fourthTwoMonthsArt(), schoolReportCaptured.getFourthTwoMonthsArt());
            assertEquals(updateSchoolReport.firstTwoMonthsPhilosophy(), schoolReportCaptured.getFirstTwoMonthsPhilosophy());
            assertEquals(updateSchoolReport.secondTwoMonthsPhilosophy(), schoolReportCaptured.getSecondTwoMonthsPhilosophy());
            assertEquals(updateSchoolReport.thirdTwoMonthsPhilosophy(), schoolReportCaptured.getThirdTwoMonthsPhilosophy());
            assertEquals(updateSchoolReport.fourthTwoMonthsPhilosophy(), schoolReportCaptured.getFourthTwoMonthsPhilosophy());
            assertEquals(updateSchoolReport.firstTwoMonthsBiology(), schoolReportCaptured.getFirstTwoMonthsBiology());
            assertEquals(updateSchoolReport.secondTwoMonthsBiology(), schoolReportCaptured.getSecondTwoMonthsBiology());
            assertEquals(updateSchoolReport.thirdTwoMonthsBiology(), schoolReportCaptured.getThirdTwoMonthsBiology());
            assertEquals(updateSchoolReport.fourthTwoMonthsBiology(), schoolReportCaptured.getFourthTwoMonthsBiology());
            assertEquals(finalMedia(updateSchoolReport.firstTwoMonthPortuguese()
                    , updateSchoolReport.secondTwoMonthsPortuguese(), updateSchoolReport.thirdTwoMonthsPortuguese()
                    , updateSchoolReport.fourthTwoMonthsPortuguese()).divide(BigDecimal.valueOf(4))
                            .setScale(1, RoundingMode.UP), schoolReportCaptured.getFinalMediaPortuguese());

            assertEquals(finalMedia(updateSchoolReport.firstTwoMonthsMathematics()
                    , updateSchoolReport.secondTwoMonthsMathematics(), updateSchoolReport.thirdTwoMonthsMathematics()
                    , updateSchoolReport.fourthTwoMonthsMathematics()).divide(BigDecimal.valueOf(4))
                            .setScale(1, RoundingMode.UP), schoolReportCaptured.getFinalMediaMathematics());


            assertEquals(finalMedia(updateSchoolReport.firstTwoMonthsHistory()
                    , updateSchoolReport.secondTwoMonthsHistory(), updateSchoolReport.thirdTwoMonthsHistory()
                    , updateSchoolReport.fourthTwoMonthsHistory()).divide(BigDecimal.valueOf(4))
                            .setScale(1, RoundingMode.UP), schoolReportCaptured.getFinalMediaHistory());


            assertEquals(finalMedia(updateSchoolReport.firstTwoMonthsScience()
                    , updateSchoolReport.secondTwoMonthsScience(), updateSchoolReport.thirdTwoMonthsScience()
                    , updateSchoolReport.fourthTwoMonthsScience()).divide(BigDecimal.valueOf(4))
                            .setScale(1, RoundingMode.UP), schoolReportCaptured.getFinalMediaScience());

            assertEquals(finalMedia(updateSchoolReport.firstTwoMonthsEnglish()
                    , updateSchoolReport.secondTwoMonthsEnglish(), updateSchoolReport.thirdTwoMonthsEnglish()
                    , updateSchoolReport.fourthTwoMonthsEnglish()).divide(BigDecimal.valueOf(4))
                            .setScale(1, RoundingMode.UP), schoolReportCaptured.getFinalMediaEnglish());

            assertEquals(finalMedia(updateSchoolReport.firstTwoMonthsArt()
                    , updateSchoolReport.secondTwoMonthsArt(), updateSchoolReport.thirdTwoMonthsArt()
                    , updateSchoolReport.fourthTwoMonthsArt()).divide(BigDecimal.valueOf(4))
                            .setScale(1, RoundingMode.UP), schoolReportCaptured.getFinalMediaArt());
            assertEquals(finalMedia(updateSchoolReport.firstTwoMonthsPhilosophy()
                    , updateSchoolReport.secondTwoMonthsPhilosophy(), updateSchoolReport.thirdTwoMonthsPhilosophy()
                    , updateSchoolReport.fourthTwoMonthsPhilosophy()).divide(BigDecimal.valueOf(4))
                            .setScale(1, RoundingMode.UP), schoolReportCaptured.getFinalMediaPhilosophy());

            assertEquals(finalMedia(updateSchoolReport.firstTwoMonthsBiology()
                    , updateSchoolReport.secondTwoMonthsBiology(), updateSchoolReport.thirdTwoMonthsBiology()
                    , updateSchoolReport.fourthTwoMonthsBiology()).divide(BigDecimal.valueOf(4))
                            .setScale(1, RoundingMode.UP), schoolReportCaptured.getFinalMediaBiology());
        }

        @Test
        @DisplayName("You should make an exception when you can't find the student")
        void YouShouldMakeAnExceptionWhenYouCantFindTheStudent(){
            doReturn(null)
                    .when(studentRepository)
                    .findByStudentRegistration(updateSchoolReport.studentRegistration());

            assertThrows(StudentNotFound.class,
                    () -> schoolReportService.updateSchoolReport(updateSchoolReport));
        }
    }
}