package com.mustache.bbs.controller;

import com.mustache.bbs.dto.HospitalResponse;
import com.mustache.bbs.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@WebMvcTest(HospitalRestController.class)
class HospitalRestControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    HospitalService hospitalService;

    @Test
    @DisplayName("MockMvc를 통한 hospital 데이터 가져오기 테스트")
    void jsonResponse() throws Exception {
        HospitalResponse hospitalResponse = HospitalResponse.builder()
                        .id(2321)
                        .roadNameAddress("서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)")
                        .hospitalName("노소아청소년과의원")
                        .businessTypeName("의원")
                        .patientRoomCount(0)
                        .totalNumberOfBeds(0)
                        .totalAreaSize(0.0f)
                        .businessStatusName("영업중").build();

        given(hospitalService.getHospitalInfo(2321))
                .willReturn(hospitalResponse);
        int hospitalId = 2321;

        //앞에서 Autowired한 MockMvc
        String url = String.format("/api/v1/hospitals/%d", hospitalId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hospitalName").exists()) // $는 루트 $아래에 hospitalName이 있어야 한다.
                .andExpect(jsonPath("$.hospitalName").value("노소아청소년과의원"))
                .andDo(print());        //http request, response 내역을 출력
        verify(hospitalService).getHospitalInfo(hospitalId);
    }

}