package com.mustache.bbs.service;

import com.mustache.bbs.domain.Hospital;
import com.mustache.bbs.dto.HospitalResponse;
import com.mustache.bbs.repository.HospitalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional
    public Page<Hospital> getHospitalByAddressAndType(String address, List<String> types, Pageable pageable){
        Page<Hospital> page = hospitalRepository.findByRoadNameAddressContainingAndBusinessTypeNameIn(address, types, pageable);
        return page;
    }

    public HospitalResponse getHospitalInfo(Integer id){
        Optional<Hospital> hospitalOpt = hospitalRepository.findById(id);
        Hospital hospital = hospitalOpt.get();
        HospitalResponse hospitalResponse = Hospital.of(hospital);
        if (hospital.getBusinessStatusCode() == 13){
            hospitalResponse.setBusinessStatusName("영업중");
        } else if (hospital.getBusinessStatusCode() == 3){
            hospitalResponse.setBusinessStatusName("폐업");
        } else {
            hospitalResponse.setBusinessStatusName("휴업");
        }
        return hospitalResponse;
    }



}
