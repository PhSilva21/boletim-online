package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.EnrollStudent;
import com.bandeira.school_report_online.dtos.SchoolCreateRequest;
import com.bandeira.school_report_online.dtos.SchoolUpdateDTO;
import com.bandeira.school_report_online.exceptions.CountyNotFound;
import com.bandeira.school_report_online.exceptions.SchoolNotFound;
import com.bandeira.school_report_online.exceptions.StudentNotFound;
import com.bandeira.school_report_online.model.County;
import com.bandeira.school_report_online.model.School;
import com.bandeira.school_report_online.model.Student;
import com.bandeira.school_report_online.repositories.CountyRepository;
import com.bandeira.school_report_online.repositories.SchoolRepository;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchoolServiceTest {

    @InjectMocks
    private SchoolService schoolService;

    @Mock
    private CountyRepository countyRepository;

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private SchoolRepository schoolRepository;

    @Captor
    private ArgumentCaptor<School> schoolArgumentCaptor;

    @Nested
    class CreateSchool {

        County county = new County(UUID.randomUUID().toString(), "São Bento", "Sorocaba");

        SchoolCreateRequest schoolCreateRequest = new SchoolCreateRequest("Ferolin abuquerque",
                30000,"São Bento");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        @Test
        @DisplayName("You must create a successful school")
        void YouMustCreateASuccessfulSchool() {
            doReturn(county)
                    .when(countyRepository).findByName(schoolCreateRequest.countyName());
            doReturn(school)
                    .when(schoolRepository).save(schoolArgumentCaptor.capture());

            var response = schoolService.createSchool(schoolCreateRequest);

            var schoolCaptured = schoolArgumentCaptor.getValue();

            assertEquals(schoolCreateRequest.name(), schoolCaptured.getName());
            assertEquals(schoolCreateRequest.vacancies(), schoolCaptured.getVacancies());
            assertEquals(schoolCreateRequest.countyName(), schoolCaptured.getCounty().getName());

            verify(countyRepository, times(1))
                    .findByName(schoolCreateRequest.countyName());
            verify(schoolRepository, times(1)).save(schoolArgumentCaptor.capture());
        }

        @Test
        @DisplayName("It should return an exception when it doesn't find the county")
        void ItShouldReturnAnExceptionWhenItDoesntFindTheCounty(){
            doReturn(null)
                    .when(countyRepository).findByName(schoolCreateRequest.countyName());

            assertThrows(CountyNotFound.class,
                    () -> schoolService.createSchool(schoolCreateRequest));

            verify(countyRepository, times(1))
                    .findByName(schoolCreateRequest.countyName());
            verify(schoolRepository, times(0)).save(schoolArgumentCaptor.capture());
        }
    }

    @Nested
    class enrollStudent {

        County county = new County(UUID.randomUUID().toString(), "São Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        Student student = new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school);

        EnrollStudent enrollStudent = new EnrollStudent("Matial da Fonseca",
                RandomString.generateRandomString(12));

        @Test
        @DisplayName("Must successfully enroll the student")
        void MustSuccessfullyEnrollTheStudent() {
            var studentList = List.of(student);
            doReturn(student).when(studentRepository)
                    .findByStudentRegistration(enrollStudent.studentRegistration());
            doReturn(school).when(schoolRepository)
                    .findByName(enrollStudent.schoolName());
            doReturn(student).when(studentRepository)
                    .save(student);
            doReturn(school).when(schoolRepository)
                    .save(school);

            schoolService.enrollStudent(enrollStudent);

            assertEquals(student.getSchool(), school);
            assertEquals(school.getStudents().size(), studentList.size());
        }

        @Test
        @DisplayName("It should return an exception when it doesn't find the county")
        void ItShouldReturnAnExceptionWhenItDoesntFindTheCounty(){
                doReturn(null)
                        .when(studentRepository)
                        .findByStudentRegistration(enrollStudent.studentRegistration());

                assertThrows(StudentNotFound.class,
                        () -> schoolService.enrollStudent(enrollStudent));

                verify(studentRepository, times(1))
                        .findByStudentRegistration(enrollStudent.studentRegistration());
                verify(schoolRepository, times(0))
                        .findByName(enrollStudent.schoolName());
                verify(studentRepository, times(0))
                        .save(student);
                verify(schoolRepository, times(0))
                        .save(school);
        }

        @Test
        @DisplayName("It should return an exception when it doesn't find the school")
        void ItShouldReturnAnExceptionWhenItDoesntFindTheSchool(){
            doReturn(student)
                    .when(studentRepository)
                    .findByStudentRegistration(enrollStudent.studentRegistration());
            doReturn(null)
                    .when(schoolRepository).findByName(enrollStudent.schoolName());

            assertThrows(SchoolNotFound.class,
                    () -> schoolService.enrollStudent(enrollStudent));

            verify(studentRepository, times(1))
                    .findByStudentRegistration(enrollStudent.studentRegistration());
            verify(schoolRepository, times(1))
                    .findByName(enrollStudent.schoolName());
            verify(studentRepository, times(0))
                    .save(student);
            verify(schoolRepository, times(0))
                    .save(school);
        }
    }

    @Nested
    class updateSchool {

        County county = new County(UUID.randomUUID().toString(), "São Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        SchoolUpdateDTO schoolUpdateDTO = new SchoolUpdateDTO(UUID.randomUUID().toString()
                ,"Geoargina Ferreira", 4000,"Teresópolis");

        @Test
        @DisplayName("Must successfully update the school")
        void MustSuccessfullyUpdateTheSchool() {
            doReturn(Optional.of(school))
                    .when(schoolRepository).findById(schoolUpdateDTO.id());
            doReturn(school)
                    .when(schoolRepository).save(schoolArgumentCaptor.capture());

            schoolService.updateSchool(schoolUpdateDTO);

            var schoolCaptured = schoolArgumentCaptor.getValue();

            assertEquals(schoolUpdateDTO.nameSchool(), schoolCaptured.getName());
            assertEquals(schoolUpdateDTO.vacancies(), schoolCaptured.getVacancies());

            verify(schoolRepository, times(1))
                    .findById(schoolUpdateDTO.id());
            verify(schoolRepository, times(1))
                    .save(schoolArgumentCaptor.capture());
        }

        @Test
        @DisplayName("It should return an exception when it doesn't find the school")
        void ItShouldReturnAnExceptionWhenItDoesntFindTheSchool(){
            doReturn(Optional.empty())
                    .when(schoolRepository).findById(schoolUpdateDTO.id());

            assertThrows(SchoolNotFound.class,
                    () -> schoolService.updateSchool(schoolUpdateDTO));

            verify(schoolRepository, times(1))
                    .findById(schoolUpdateDTO.id());
            verify(schoolRepository, times(0))
                    .save(schoolArgumentCaptor.capture());
        }
    }

    @Nested
    @DisplayName("Should return list of schools by county name")
    class ShouldReturnListOfSchoolByCountyName {

        County county = new County(UUID.randomUUID().toString(), "São Bento", "Sorocaba");

        County county2 = new County(UUID.randomUUID().toString(), "São Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        School school2 = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county2);

        @Test
        void findBsyCounty() {
            var schoolList = List.of(school, school2);
            doReturn(county)
                    .when(countyRepository).findByName(county.getName());

            county.getSchools().add(school);

            var response = schoolService.findByCounty(county.getName());

            assertEquals( response.size(), schoolList.stream().filter(
                    s -> s.getCounty().equals(county)).toList().size());
        }

        @Test
        @DisplayName("It should return an exception when it doesn't find the county")
        void ItShouldReturnAnExceptionWhenItDoesntFindTheCounty(){
            doReturn(null)
                    .when(countyRepository).findByName(county.getName());

            assertThrows(CountyNotFound.class,
                    () -> schoolService.findByCounty(county.getName()));
        }
    }
}