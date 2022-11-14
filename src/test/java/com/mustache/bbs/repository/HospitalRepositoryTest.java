package com.mustache.bbs.repository;

import com.mustache.bbs.domain.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {
    @Autowired
    HospitalRepository hospitalRepository;
    @Test
    void findById(){
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(1);
        Hospital hp = hospitalOptional.get();
        assertEquals(1, hp.getId());
    }
    @Test
    @DisplayName("업종별 병원리스트 검색")
    void findByBusinessTypeNameIn(){
        List<String> types = new ArrayList<>();
        types.add("보건소");
        types.add("보건진료소");
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(types);
        assertEquals(2113, hospitals.size());
    }

}