package com.mustache.bbs.service;

import com.mustache.bbs.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HospitalService {
    HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public List<Hospital> search (String keyword, Pageable pageable){
        List<Hospital> hospitalList = hospitalRepository.findByHospitalNameContaining(keyword, pageable);
        return hospitalList;
    }

    @Transactional
    public Page<Hospital> getHospitalList(Pageable pageable){
        return hospitalRepository.findAll(pageable);
    }


}
