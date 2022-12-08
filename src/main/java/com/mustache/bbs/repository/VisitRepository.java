package com.mustache.bbs.repository;

import com.mustache.bbs.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByUserId (Long id);
    List<Visit> findByHospitalId(Integer id);
}
