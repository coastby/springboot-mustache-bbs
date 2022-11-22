package com.mustache.bbs.service;

import com.mustache.bbs.domain.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HospitalServiceTest {
    private HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);
    private HospitalService hospitalService;

    @BeforeEach
    void setUp(){
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    @DisplayName("영업상태 표시 확인")
    void getById(){
        Hospital givenHospital = new Hospital(
                .Builder()
                .id(1)
                .hospitalName("").
        );

        Mockito.when(hospitalRepository.findById(1)).
                thenReturn(Optional.of(givenHospital));
    }
}