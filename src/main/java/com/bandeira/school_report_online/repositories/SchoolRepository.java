package com.bandeira.school_report_online.repositories;

import com.bandeira.school_report_online.model.School;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchoolRepository extends MongoRepository<School, String> {

}
