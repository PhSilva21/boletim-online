package com.bandeira.school_report_online.repositories;

import com.bandeira.school_report_online.model.SchoolEnrollment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchoolEnrollmentRepository extends MongoRepository<SchoolEnrollment, String> {

    SchoolEnrollment findByProtocol(String number);
}
