package com.mustache.bbs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
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
    private String businessStatusName;

    public HospitalResponse(Integer id, String roadNameAddress, String hospitalName, String businessTypeName, int patientRoomCount, int totalNumberOfBeds, float totalAreaSize) {
        this.id = id;
        this.roadNameAddress = roadNameAddress;
        this.hospitalName = hospitalName;
        this.businessTypeName = businessTypeName;
        this.patientRoomCount = patientRoomCount;
        this.totalNumberOfBeds = totalNumberOfBeds;
        this.totalAreaSize = totalAreaSize;
    }

    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }
}
