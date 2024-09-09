package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.StudentCreateRequest;
import com.bandeira.school_report_online.dtos.UpdateResponsibleStudent;
import com.bandeira.school_report_online.dtos.UpdateStudentCounty;
import com.bandeira.school_report_online.dtos.UpdateStudentSchool;
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
class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SchoolRepository schoolRepository;

    @Mock
    private CountyRepository countyRepository;

    @Captor
    private ArgumentCaptor<Student> studentArgumentCaptor;

    @Nested
    class createStudent {

        County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        Student student = new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school);

        StudentCreateRequest studentCreateRequest = new StudentCreateRequest("Mateus Claudio"
        ,"27217171217812", "maria de Jesus",11948646118L, "Napoleão"
                , "Aricanduva");

        @Test
        @DisplayName("Must successfully create student")
        void MustSuccessfullyCreateStudent() {
            doReturn(school)
                    .when(schoolRepository).findByName(studentCreateRequest.schoolName());
            doReturn(county)
                    .when(countyRepository).findByName(studentCreateRequest.countyName());
            doReturn(student)
                    .when(studentRepository).save(studentArgumentCaptor.capture());

            var response = studentService.createStudent(studentCreateRequest);

            var studentCaptured = studentArgumentCaptor.getValue();

            assertEquals(studentCreateRequest.name(), studentCaptured.getName());
            assertEquals(studentCreateRequest.cpf(), studentCaptured.getCpf());
            assertEquals(studentCreateRequest.responsible(), studentCaptured.getResponsible());
            assertEquals(studentCreateRequest.celResponsible(), studentCaptured.getCelResponsible());
            assertEquals(county, studentCaptured.getCounty());
            assertEquals(school, studentCaptured.getSchool());

            verify(schoolRepository, times(1))
                    .findByName(studentCreateRequest.schoolName());
            verify(countyRepository, times(1))
                    .findByName(studentCreateRequest.countyName());
            verify(studentRepository, times(1))
                    .save(studentArgumentCaptor.capture());
        }

        @Test @DisplayName("It should return an exception when it doesn't find the school")
        void ItShouldReturnAnExceptionWhenItDoesntFindTheSchool(){
            doReturn(null)
                    .when(schoolRepository).findByName(studentCreateRequest.schoolName());

            assertThrows(SchoolNotFound.class,
                    () -> studentService.createStudent(studentCreateRequest));

            verify(schoolRepository, times(1))
                    .findByName(studentCreateRequest.schoolName());
            verify(countyRepository, times(0))
                    .findByName(studentCreateRequest.countyName());
            verify(studentRepository, times(0))
                    .save(studentArgumentCaptor.capture());
        }

        @Test @DisplayName("It should return an exception when it doesn't find the county")
        void ItShouldReturnAnExceptionWhenItDoesntFindTheCounty(){
            doReturn(school)
                    .when(schoolRepository).findByName(studentCreateRequest.schoolName());
            doReturn(null)
                    .when(countyRepository).findByName(studentCreateRequest.countyName());

            assertThrows(CountyNotFound.class,
                    () -> studentService.createStudent(studentCreateRequest));

            verify(schoolRepository, times(1))
                    .findByName(studentCreateRequest.schoolName());
            verify(countyRepository, times(1))
                    .findByName(studentCreateRequest.countyName());
            verify(studentRepository, times(0))
                    .save(studentArgumentCaptor.capture());
        }
    }

    @Nested
    class findByCounty {

        County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

        County county2 = new County(UUID.randomUUID().toString(), "Jandira", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        Student student = new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school);

        Student student2 = new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county2, school);

        @Test
        @DisplayName("Should return list of students by county")
        void ShouldReturnListOfStudentByCounty() {
            var studentList = List.of(student, student2);
            doReturn(studentList)
                    .when(studentRepository).findAll();

            var response = studentService.findByCounty(county.getName());

            assertEquals( response.size(), studentList.stream()
                    .filter(s -> s.getCounty().getName().equals(county.getName())).toList().size());
        }
    }

    @Nested
    class findBySchool {

        County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        School school2 = new School(UUID.randomUUID().toString(), "Abreu sátira", 3200, county);

        Student student = new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school);

        Student student2 = new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school2);

        @Test
        @DisplayName("Should return list of students by school")
        void ShouldReturnListOfStudentBySchool() {
            var studentList = List.of(student, student2);
            doReturn(studentList)
                    .when(studentRepository).findAll();

            var response = studentService.findBySchool(school.getName());

            assertEquals(response.size(), studentList.stream()
                    .filter(s -> s.getSchool().getName().equals(school.getName())).toList().size());
        }
    }

    @Nested
    class findById {

        County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        Student student = new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school);

        @Test
        @DisplayName("Must return student by id successfully")
        void MustReturnStudentByIdSuccessfully() {
            doReturn(Optional.of(student))
                    .when(studentRepository).findById(student.getId());

            var response = studentService.findById(student.getId());

            assertNotNull(response);
        }

        @Test
        @DisplayName("It should return an exception when it can't find the student")
        void ItShouldReturnArExceptionWhenItCantFindTheStudent(){
            doReturn(Optional.empty())
                    .when(studentRepository).findById(student.getId());

            assertThrows(StudentNotFound.class, () ->
                    studentService.findById(student.getId()));
        }
    }

    @Nested
    class updateResponsible {

        County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        Student student = new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school);

        UpdateResponsibleStudent updateResponsibleStudent = new UpdateResponsibleStudent(UUID.randomUUID().toString()
                , "Claudio Fonseca", 119376176313L);

        @Test
        @DisplayName("Must update responsible successfully")
        void MustUpdateResponsibleSuccessfully() {
            doReturn(Optional.of(student))
                    .when(studentRepository).findById(updateResponsibleStudent.id());
            doReturn(student)
                    .when(studentRepository).save(studentArgumentCaptor.capture());

            studentService.updateResponsible(updateResponsibleStudent);

            var studentCaptured = studentArgumentCaptor.getValue();

            assertEquals(updateResponsibleStudent.nameResponsible(), studentCaptured.getResponsible());
            assertEquals(updateResponsibleStudent.cel(), studentCaptured.getCelResponsible());

            verify(studentRepository, times(1))
                    .findById(updateResponsibleStudent.id());
            verify(studentRepository, times(1))
                    .save(studentArgumentCaptor.capture());
        }

        @Test
        @DisplayName("It should return an exception when it can't find the student")
        void ItShouldReturnArExceptionWhenItCantFindTheStudent(){
            doReturn(Optional.empty())
                    .when(studentRepository).findById(updateResponsibleStudent.id());

            assertThrows(StudentNotFound.class,
                    () -> studentService.updateResponsible(updateResponsibleStudent));

            verify(studentRepository, times(1))
                    .findById(updateResponsibleStudent.id());
            verify(studentRepository, times(0))
                    .save(studentArgumentCaptor.capture());
        }
    }

    @Nested
    class updateCounty {

        County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

        School school = new School(UUID.randomUUID().toString(), "Napoleão", 3200, county);

        Student student = new Student(UUID.randomUUID().toString(), RandomString.generateRandomString(12),
                "Mateus Henrique", "16351636131311", "Maria de Jesus", 382327227222L
                , county, school);

        UpdateStudentCounty updateStudentCounty = new UpdateStudentCounty(UUID.randomUUID().toString()
        , "Jaguára");

        @Test
        @DisplayName("Must update responsible successfully")
        void MustUpdateResponsibleSuccessfully() {
            doReturn(Optional.of(student))
                    .when(studentRepository).findById(updateStudentCounty.id());
            doReturn(county)
                    .when(countyRepository).findByName(updateStudentCounty.nameCounty());
            doReturn(student)
                    .when(studentRepository).save(studentArgumentCaptor.capture());

            studentService.updateCounty(updateStudentCounty);

            var studentCaptured = studentArgumentCaptor.getValue();

            assertEquals(county, studentCaptured.getCounty());

            verify(studentRepository, times(1))
                    .findById(updateStudentCounty.id());
            verify(countyRepository, times(1))
                    .findByName(updateStudentCounty.nameCounty());
            verify(studentRepository, times(1))
                    .save(studentArgumentCaptor.capture());
        }

        @Test
        @DisplayName("It should return an exception when it can't find the student")
        void ItShouldReturnArExceptionWhenItCantFindTheStudent(){
            doReturn(Optional.empty())
                    .when(studentRepository).findById(updateStudentCounty.id());

            assertThrows(StudentNotFound.class,
                    () -> studentService.updateCounty(updateStudentCounty));

            verify(studentRepository, times(1))
                    .findById(updateStudentCounty.id());
            verify(countyRepository, times(0))
                    .findByName(updateStudentCounty.nameCounty());
            verify(studentRepository, times(0))
                    .save(studentArgumentCaptor.capture());
        }

        @Test
        @DisplayName("It should return an exception when it can't find the county")
        void ItShouldReturnArExceptionWhenItCantFindTheCounty(){
            doReturn(Optional.of(student))
                    .when(studentRepository).findById(updateStudentCounty.id());
            doReturn(null)
                    .when(countyRepository).findByName(updateStudentCounty.nameCounty());

            assertThrows(CountyNotFound.class,
                    () -> studentService.updateCounty(updateStudentCounty));

            verify(studentRepository, times(1))
                    .findById(updateStudentCounty.id());
            verify(countyRepository, times(1))
                    .findByName(updateStudentCounty.nameCounty());
            verify(studentRepository, times(0))
                    .save(studentArgumentCaptor.capture());
        }
    }
}