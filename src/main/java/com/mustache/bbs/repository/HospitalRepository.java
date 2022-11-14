package com.mustache.bbs.repository;

import com.mustache.bbs.domain.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    //키워드를 포함하는 병원명이며 상태가 정상인 병원 리스트 검색
    Page<Hospital> findByHospitalNameContainingAndAndBusinessStatusIs(String keyword, Integer status, Pageable pageable);
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
    Page<Hospital> findByRoadNameAddressContainingAndBusinessTypeNameIn(String address, List<String> businessTypes, Pageable pageable);
}
