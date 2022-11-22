package com.mustache.bbs.domain;

import com.mustache.bbs.dto.HospitalResponse;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
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

    public static HospitalResponse of(Hospital hospital){
        return new HospitalResponse(hospital.getId(), hospital.getRoadNameAddress(), hospital.getHospitalName(), hospital.getBusinessTypeName(), hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(), hospital.getTotalAreaSize());
    }
}
