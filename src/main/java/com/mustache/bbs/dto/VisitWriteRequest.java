package com.mustache.bbs.dto;

import com.mustache.bbs.domain.Visit;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VisitWriteRequest {
    private Integer hospitalId;
    private String content;
    private String disease;
    private long cost;
}
