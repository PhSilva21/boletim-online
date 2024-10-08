package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.CountyCreateRequest;
import com.bandeira.school_report_online.exceptions.CountyNotFound;
import com.bandeira.school_report_online.model.County;
import com.bandeira.school_report_online.repositories.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CountyService {

    @Autowired
    private CountyRepository countyRepository;


    public CountyCreateRequest createCounty(CountyCreateRequest countyCreateRequest){

        County county = new County(
                UUID.randomUUID().toString(),
                countyCreateRequest.name(),
                countyCreateRequest.uf()
        );

        countyRepository.save(county);

        return countyCreateRequest;
    }

    public List<County> findAll(){
        return  countyRepository.findAll();
    }

    public void deleteById(String id){
        var county = countyRepository.findById(id).orElseThrow(CountyNotFound::new);

        countyRepository.deleteById(id);
    }

}
