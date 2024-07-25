package com.bandeira.school_report_online.repositories;

import com.bandeira.school_report_online.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

    Student findByName(String name);

    Student findByStudentRegistration(String studentRegistration);
}
