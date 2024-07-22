package com.bandeira.school_report_online.repositories;

import com.bandeira.school_report_online.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends MongoRepository<User, String> {

    UserDetails findByUsername(String username);
}
