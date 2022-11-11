package com.mustache.bbs.repository;

import com.mustache.bbs.entity.Hospital;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}