package com.mustache.bbs.service;

import com.mustache.bbs.domain.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HospitalService {
    HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public Page<Hospital> searchWorkingHospital(String keyword, Pageable pageable){
        Page<Hospital> hospitalList = hospitalRepository.findByHospitalNameContainingAndAndBusinessStatusIs(keyword, 1, pageable);
        return hospitalList;
    }

    @Transactional
    public Page<Hospital> getHospitalList(Pageable pageable){
        return hospitalRepository.findAll(pageable);
    }
    public Hospital getHospital(Integer id){
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        if(!hospitalOptional.isEmpty()){
            return hospitalOptional.get();
        } else{
            return null;
        }
    }

}
