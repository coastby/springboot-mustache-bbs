package com.mustache.bbs.dto;

import com.mustache.bbs.domain.Visit;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VisitResponse {
    private String hospitalName;
    private String userId;
    private String content;
    private String disease;
    private long cost;

    public static VisitResponse from(Visit visit){
        return VisitResponse.builder()
                .hospitalName(visit.getHospital().getHospitalName())
                .userId(visit.getUser().getUserId())
                .content(visit.getContent())
                .disease(visit.getDisease())
                .cost(visit.getCost())
                .build();
    }
}
