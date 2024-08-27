package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.SchoolEnrollmentRequest;
import com.bandeira.school_report_online.dtos.UpdateSchoolEnrollment;
import com.bandeira.school_report_online.exceptions.SchoolEnrollmentNotFound;
import com.bandeira.school_report_online.exceptions.SchoolNotFound;
import com.bandeira.school_report_online.exceptions.StudentNotFound;
import com.bandeira.school_report_online.model.*;
import com.bandeira.school_report_online.repositories.SchoolEnrollmentRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchoolEnrollmentServiceTest {


    @InjectMocks
    private SchoolEnrollmentService schoolEnrollmentService;

    @Mock
    private SchoolEnrollmentRepository schoolEnrollmentRepository;

    @Mock
    private SchoolRepository schoolRepository;

    @Captor
    private ArgumentCaptor<SchoolEnrollment> schoolEnrollmentArgumentCapture;

    @Mock
    private StudentRepository studentRepository;
    

    @Nested
    class registerForSchool {

        County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        Student student= new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school);

        SchoolEnrollment schoolEnrollment = new SchoolEnrollment(UUID.randomUUID().toString(), student, school
                , RandomString.generateRandomString(8).toUpperCase(), StatusSchoolEnrollment.PROCESSING);

        SchoolEnrollmentRequest schoolEnrollmentRequest = new SchoolEnrollmentRequest("Rafael Lopez Macedo", "João Carlos Azevedo"
        );

        @Test
        @DisplayName("You must start enrolling the student in a school")
        void YouMustStartEnrollingTheStudentInASchool() {

            doReturn(school)
                    .when(schoolRepository)
                    .findByName(schoolEnrollmentRequest.nameSchool());
            doReturn(student)
                    .when(studentRepository)
                    .findByStudentRegistration(schoolEnrollmentRequest.studentRegistration());
            doReturn(schoolEnrollment)
                    .when(schoolEnrollmentRepository)
                    .save(schoolEnrollmentArgumentCapture.capture());


            var response = schoolEnrollmentService.registerForSchool(schoolEnrollmentRequest);

            var schoolEnrollmentCaptured = schoolEnrollmentArgumentCapture.getValue();


            assertEquals(school, schoolEnrollmentCaptured.getSchool());
            assertEquals(student, schoolEnrollmentCaptured.getStudent());

            verify(schoolRepository, times(1))
                    .findByName(schoolEnrollmentRequest.nameSchool());
            verify(studentRepository, times(1))
                    .findByStudentRegistration(schoolEnrollmentRequest.studentRegistration());
            verify(schoolEnrollmentRepository, times(1))
                    .save(schoolEnrollmentCaptured);
        }

        @Test
        @DisplayName("It should return an error when it can't find the school")
        void ItShouldReturnAnErrorWhenItCantFindTheSchool(){

            doReturn(null)
                    .when(schoolRepository)
                    .findByName(schoolEnrollmentRequest.nameSchool());

            assertThrows(SchoolNotFound.class,
                    () -> schoolEnrollmentService.registerForSchool(schoolEnrollmentRequest));

            verify(schoolRepository, times(1))
                    .findByName(schoolEnrollmentRequest.nameSchool());
            verify(studentRepository, times(0))
                    .findByStudentRegistration(schoolEnrollmentRequest.studentRegistration());
            verify(schoolEnrollmentRepository, times(0))
                    .save(schoolEnrollment);
        }

        @Test
        @DisplayName("It should return an error when it can't find the student")
        void ItShouldReturnAnErrorWhenItCantFindTheStudent() {
            
            doReturn(school)
                    .when(schoolRepository)
                    .findByName(schoolEnrollmentRequest.nameSchool());
            doReturn(null)
                    .when(studentRepository)
                    .findByStudentRegistration(schoolEnrollmentRequest.studentRegistration());


            assertThrows(StudentNotFound.class,
                    () -> schoolEnrollmentService.registerForSchool(schoolEnrollmentRequest));

            verify(schoolRepository, times(1))
                    .findByName(schoolEnrollmentRequest.nameSchool());
            verify(studentRepository, times(1))
                    .findByStudentRegistration(schoolEnrollmentRequest.studentRegistration());
            verify(schoolEnrollmentRepository, times(0))
                    .save(schoolEnrollment);
        }

    }

    @Nested
    class findByProtocol {

        County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        Student student= new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school);

        SchoolEnrollment schoolEnrollment = new SchoolEnrollment(UUID.randomUUID().toString(), student, school
                , RandomString.generateRandomString(8).toUpperCase(), StatusSchoolEnrollment.PROCESSING);


        @Test
        @DisplayName("Must return school enrollment successfully")
        void MustReturnSchoolEnrollmentSuccessfully() {

            var list = List.of(schoolEnrollment);
            doReturn(schoolEnrollment)
                    .when(schoolEnrollmentRepository)
                    .findByProtocol(schoolEnrollment.getProtocol());

            var response = schoolEnrollmentService.findByProtocol(schoolEnrollment.getProtocol());


            assertNotNull(response);
        }

        @Test
        @DisplayName("It should return an error when it doesn't find the school enrollment")
        void ItShouldReturnAnErrorWhenItDoesntFindTheSchoolEnrollment() {
            doReturn(null)
                    .when(schoolEnrollmentRepository)
                    .findByProtocol(schoolEnrollment.getProtocol());

            assertThrows(SchoolEnrollmentNotFound.class,
                    () -> schoolEnrollmentService.findByProtocol(schoolEnrollment.getProtocol()));
        }
    }

    @Nested
    class updateSchool {

        County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        Student student = new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school);

        SchoolEnrollment schoolEnrollment = new SchoolEnrollment(UUID.randomUUID().toString(), student, school
                , RandomString.generateRandomString(8).toUpperCase(), StatusSchoolEnrollment.PROCESSING);

        UpdateSchoolEnrollment updateSchoolEnrollment = new UpdateSchoolEnrollment(RandomString.generateRandomString(12), "João Sarto");


        @Test
        @DisplayName("Must successfully update the school")
        void MustSuccessfullyUpdateTheSchool() {

            doReturn(schoolEnrollment)
                    .when(schoolEnrollmentRepository)
                    .findByProtocol(updateSchoolEnrollment.protocol());
            doReturn(school)
                    .when(schoolRepository)
                    .findByName(updateSchoolEnrollment.nameSchool());
            doReturn(schoolEnrollment)
                    .when(schoolEnrollmentRepository)
                    .save(schoolEnrollmentArgumentCapture.capture());

            schoolEnrollmentService.updateSchool(updateSchoolEnrollment);

            var schoolEnrollmentCaptured = schoolEnrollmentArgumentCapture.getValue();

            assertEquals(schoolEnrollmentCaptured.getSchool(), school);

            verify(schoolEnrollmentRepository, times(1))
                    .findByProtocol(updateSchoolEnrollment.protocol());
            verify(schoolRepository, times(1))
                    .findByName(updateSchoolEnrollment.nameSchool());
            verify(schoolEnrollmentRepository, times(1))
                    .save(schoolEnrollment);
        }

        @Test
        @DisplayName("It should return an error when it doesn't find the school enrollment")
        void ItShouldReturnAnErrorWhenItDoesntFindTheSchoolEnrollment() {

            doReturn(null)
                    .when(schoolEnrollmentRepository)
                    .findByProtocol(updateSchoolEnrollment.protocol());


            assertThrows(SchoolEnrollmentNotFound.class,
                    () -> schoolEnrollmentService.updateSchool(updateSchoolEnrollment));

            verify(schoolEnrollmentRepository, times(1))
                    .findByProtocol(updateSchoolEnrollment.protocol());
            verify(schoolRepository, times(0))
                    .findByName(updateSchoolEnrollment.nameSchool());
            verify(schoolEnrollmentRepository, times(0))
                    .save(schoolEnrollment);
        }

        @Test
        @DisplayName("It should return an error when it doesn't find the school")
        void ItShouldReturnAnErrorWhenItDoesntFindTheSchool() {

            doReturn(schoolEnrollment)
                    .when(schoolEnrollmentRepository)
                    .findByProtocol(updateSchoolEnrollment.protocol());
            doReturn(null)
                    .when(schoolRepository)
                    .findByName(updateSchoolEnrollment.nameSchool());


            assertThrows(SchoolNotFound.class,
                    () -> schoolEnrollmentService.updateSchool(updateSchoolEnrollment));

            verify(schoolEnrollmentRepository, times(1))
                    .findByProtocol(updateSchoolEnrollment.protocol());
            verify(schoolRepository, times(1))
                    .findByName(updateSchoolEnrollment.nameSchool());
            verify(schoolEnrollmentRepository, times(0))
                    .save(schoolEnrollment);
        }
    }


    @Nested
    class deleteById {

        County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        Student student= new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school);

        SchoolEnrollment schoolEnrollment = new SchoolEnrollment(UUID.randomUUID().toString(), student, school
                , RandomString.generateRandomString(8).toUpperCase(), StatusSchoolEnrollment.PROCESSING);

        @Test
        @DisplayName("Must delete the school enrolment successfully")
        void MustDeleteTheSchoolEnrollmentSuccessfully() {

            doReturn(Optional.of(schoolEnrollment))
                    .when(schoolEnrollmentRepository)
                    .findById(schoolEnrollment.getId());
            doNothing()
                    .when(schoolEnrollmentRepository)
                    .deleteById(schoolEnrollment.getId());

            schoolEnrollmentService.deleteById(schoolEnrollment.getId());

            verify(schoolEnrollmentRepository, times(1))
                    .findById(schoolEnrollment.getId());
            verify(schoolEnrollmentRepository, times(1))
                    .deleteById(schoolEnrollment.getId());
        }

        @Test
        @DisplayName("It should return an error when it doesn't find the school enrollment")
        void ItShouldReturnAnErrorWhenItDoesntFindTheSchoolEnrollment() {

            doReturn(Optional.empty())
                    .when(schoolEnrollmentRepository)
                    .findById(schoolEnrollment.getId());

            assertThrows(SchoolEnrollmentNotFound.class,
                    () -> schoolEnrollmentService.deleteById(schoolEnrollment.getId()));
        }
    }
}