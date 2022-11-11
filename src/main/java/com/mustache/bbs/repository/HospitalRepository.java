package com.mustache.bbs.repository;

import com.mustache.bbs.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    Page<Hospital> findByHospitalNameContaining(String keyword, Pageable pageable);
}
