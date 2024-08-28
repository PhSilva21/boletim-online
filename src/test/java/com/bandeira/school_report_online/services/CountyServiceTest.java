package com.bandeira.school_report_online.services;

import com.bandeira.school_report_online.dtos.CountyCreateRequest;
import com.bandeira.school_report_online.exceptions.CountyNotFound;
import com.bandeira.school_report_online.model.County;
import com.bandeira.school_report_online.repositories.CountyRepository;
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
class CountyServiceTest {

    @InjectMocks
    private CountyService countyService;

    @Mock
    private CountyRepository countyRepository;

    @Captor
    private ArgumentCaptor<County> countyArgumentCaptor;

    @Nested
    class createCounty {

        @Test
        @DisplayName("You must create the county successfully")
        void YouMustCreateTheCountySuccessfully() {
            County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

            CountyCreateRequest countyCreateRequest = new CountyCreateRequest("São Bento", "SP");

            doReturn(county)
                    .when(countyRepository)
                    .save(countyArgumentCaptor.capture());

            var response = countyService.createCounty(countyCreateRequest);

            var countyCaptured = countyArgumentCaptor.getValue();


            assertEquals(countyCreateRequest.name(), countyCaptured.getName());
            assertEquals(countyCreateRequest.uf(), countyCaptured.getUf());
        }
    }

    @Nested
    class findAll {

        @Test
        @DisplayName("Should return list of successful counties")
        void ShouldReturnListOfSuccessfullyCounties(){
            County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

            var countyList = List.of(county);

            doReturn(countyList)
                    .when(countyRepository)
                    .findAll();

            var response = countyService.findAll();

            assertEquals(response.size(), countyList.size());
        }
    }


    @Nested
    class deleteById {

        @Test
        @DisplayName("It should successfully delete the county")
        void ItShouldSuccessfullyDeleteTheCounty() {

            County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

            doReturn(Optional.of(county))
                    .when(countyRepository)
                    .findById(county.getId());

            doNothing()
                    .when(countyRepository)
                    .deleteById(county.getId());

            countyService.deleteById(county.getId());

            verify(countyRepository, times(1))
                    .findById(county.getId());
            verify(countyRepository, times(1))
                    .deleteById(county.getId());
        }

        @Test
        @DisplayName("Must return exception when county not found")
        void MustReturnExceptionWhenCountyNotFound() {
            County county = new County(UUID.randomUUID().toString(), "Sao Bento", "Sorocaba");

            doReturn(Optional.empty())
                    .when(countyRepository)
                    .findById(county.getId());

            assertThrows(CountyNotFound.class,
                    () -> countyService.deleteById(county.getId()));

            verify(countyRepository, times(1))
                    .findById(county.getId());
            verify(countyRepository, times(0))
                    .deleteById(county.getId());
        }
    }
}