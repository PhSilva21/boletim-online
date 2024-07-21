package com.bandeira.school_report_online.repositories;

import com.bandeira.school_report_online.model.County;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CountyRepository extends MongoRepository<County, String> {

    @Query
    County findByName(String name);
}
