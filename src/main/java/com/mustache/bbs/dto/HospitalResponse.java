package com.mustache.bbs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class HospitalResponse {
    private Integer id;
//    private String openServiceName;
//    private int openLocalGovernmentCode;
//    private String managementNumber;
//    private LocalDateTime licenseDate;
//    private int businessStatus;
//    private int businessStatusCode;
//    private String phone;
//    private String fullAddress;
    private String roadNameAddress;
    private String hospitalName;
    private String businessTypeName;
//    private int healthcareProviderCount ;
    private int patientRoomCount;
    private int totalNumberOfBeds;
    private float totalAreaSize;
}
