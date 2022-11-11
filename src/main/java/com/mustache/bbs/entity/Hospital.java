package com.mustache.bbs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "hospitals_in_korea")
public class Hospital {
    @Id
    private Integer id;
    private String openServiceName;
    private int openLocalGovernmentCode;
    private String managementNumber;
    private LocalDateTime licenseDate;
    private int businessStatus;
    private int businessStatusCode;
    private String phone;
    private String fullAddress;
    private String roadNameAddress;
    private String hospitalName;
    private String businessTypeName;
    private int healthcareProviderCount ;
    private int patientRoomCount;
    private int totalNumberOfBeds;
    private float totalAreaSize;
}