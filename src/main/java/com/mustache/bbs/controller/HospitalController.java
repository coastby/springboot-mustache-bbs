package com.mustache.bbs.controller;

import com.mustache.bbs.domain.Hospital;
import com.mustache.bbs.dto.HospitalListDto;
import com.mustache.bbs.service.HospitalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {
    HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }
    //검색한 리스트 보여준다
    @GetMapping(value = "/search")
    public String showSearchList(String keyword, @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model){
        Page<Hospital> hospitals = hospitalService.searchWorkingHospital(keyword, pageable);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber()+"&keyword="+keyword);
        model.addAttribute("next", pageable.next().getPageNumber()+"&keyword="+keyword);
        model.addAttribute("nowPage", pageable.getPageNumber()+1);
        model.addAttribute("totalPage", hospitals.getTotalPages());

        return "hospitals/list";
    }
    //전체 리스트를 보여준다
    @GetMapping(value = "/list")
    public String showList(Model model, @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Hospital> page = hospitalService.getHospitalList(pageable);
        model.addAttribute("hospitals", page);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("nowPage", pageable.getPageNumber()+1);
        model.addAttribute("totalPage", page.getTotalPages());
        return "hospitals/list";
    }
    //병원 상세페이지
    @GetMapping(value="/{id}")
    public String showHospital(Model model, @PathVariable Integer id){
        Hospital hospital = hospitalService.getHospital(id);
        if(hospital != null){
            model.addAttribute("hospital", hospital);
            LocalDateTime date = hospital.getLicenseDate();
            int year = date.getYear();
            model.addAttribute("licenceYear", year);
            return "hospitals/show";
        } else{
            return "hospitals/error";
        }
    }
    //리스트를 병원 주소 및 업태로 필터링하여 보여준다.
    @GetMapping(value="/filter")
    public String filteredList(HospitalListDto hospitalListDto, Model model, @PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        if(hospitalListDto == null){
            return "hospitals/list";
        }
        Page<Hospital> hospitals = hospitalService.getHospitalByAddressAndType(hospitalListDto, pageable);
        String url = String.format("&address=%s&types=", hospitalListDto.getAddress(), hospitalListDto.getStringOfTypes());
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber()+url);
        model.addAttribute("next", pageable.next().getPageNumber()+url);
        model.addAttribute("nowPage", pageable.getPageNumber()+1);
        model.addAttribute("totalPage", hospitals.getTotalPages());

        return "hospitals/list";
    }

}
