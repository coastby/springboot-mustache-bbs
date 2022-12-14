package com.mustache.bbs.repository;

import com.mustache.bbs.domain.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
    @Test
    @DisplayName("특정 지역의 특정 업태의 병원 검색")
    void findByAddressAndBusinessType(){
        PageRequest pageable = PageRequest.of(0, 10);
        String address = "용산구";
        List<String> types = new ArrayList<>();
        types.add("한의원");
        Page<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContainingAndBusinessTypeNameIn(address, types, pageable);
        assertEquals(93, hospitals.getTotalElements());
    }
    @Test
    @DisplayName("병상 개수에 따른 병원 리스트")
    void findByTotalNumberOfBeds(){
        int min = 10;
        int max = 20;
        List<Hospital> hospitals = hospitalRepository.findByTotalNumberOfBedsBetween(min, max);
        for (int i = 0; i < 200; i++) {
            System.out.print(hospitals.get(i).getHospitalName()+"\t");
            System.out.println(hospitals.get(i).getTotalNumberOfBeds());

        }
        assertEquals(1592, hospitals.size());
    }
}