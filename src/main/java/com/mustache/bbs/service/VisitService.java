package com.mustache.bbs.service;

import com.mustache.bbs.domain.Hospital;
import com.mustache.bbs.domain.User;
import com.mustache.bbs.domain.Visit;
import com.mustache.bbs.dto.VisitResponse;
import com.mustache.bbs.dto.VisitWriteRequest;
import com.mustache.bbs.dto.VisitWriteResponse;
import com.mustache.bbs.repository.HospitalRepository;
import com.mustache.bbs.repository.UserRepository;
import com.mustache.bbs.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;

    public VisitWriteResponse write(VisitWriteRequest request, String userId){
        User writer = userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("올바르지 않은 아이디입니다."));
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("해당되는 병원이 없습니다."));
        LocalDateTime now = LocalDateTime.now();
        Visit visit = Visit.builder()
                .disease(request.getDisease())
                .cost(request.getCost())
                .content(request.getContent())
                .createdDate(now)
                .user(writer)
                .hospital(hospital)
                .build();
        Visit saved = visitRepository.save(visit);
        return VisitWriteResponse.from(saved);
    }


    public List<VisitResponse> showVisitList() {
        List<Visit> visits = visitRepository.findAll();
        return visits.stream().map(VisitResponse::from).collect(Collectors.toList());
    }
}
