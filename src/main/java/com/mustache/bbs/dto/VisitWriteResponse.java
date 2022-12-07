package com.mustache.bbs.dto;

import com.mustache.bbs.domain.User;
import com.mustache.bbs.domain.Visit;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VisitWriteResponse {
    private String userId;
    private String content;
    private String disease;
    private long cost;

    public static VisitWriteResponse from(Visit visit){
        return VisitWriteResponse.builder()
                .userId(visit.getUser().getUserId())
                .content(visit.getContent())
                .disease(visit.getDisease())
                .cost(visit.getCost())
                .build();
    }
}
