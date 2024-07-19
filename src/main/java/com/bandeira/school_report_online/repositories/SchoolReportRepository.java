package com.bandeira.school_report_online.repositories;

import com.bandeira.school_report_online.model.SchoolReport;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SchoolReportRepository extends MongoRepository<SchoolReport, String> {
    
}
